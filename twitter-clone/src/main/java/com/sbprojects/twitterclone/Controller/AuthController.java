package com.sbprojects.twitterclone.Controller;

import com.sbprojects.twitterclone.Repository.UserRepository;
import com.sbprojects.twitterclone.Reposne.AuthResponse;
import com.sbprojects.twitterclone.Service.CustomUserDetailsServiceImplementation;
import com.sbprojects.twitterclone.config.JwtProvider;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.User;
import com.sbprojects.twitterclone.model.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsServiceImplementation customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        System.out.println("SIGNUP endpoint hit");

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String birthDate = user.getBirthDate();

        User isEmailExists = userRepository.findByEmail(email);
        if(isEmailExists!=null){
            throw new UserException("Email is already in use with another account");
        }

        //Create a new User
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVerification(new Verification());

        //Save newly created user
        //This new .save() is automatically provided by JPARepository
        User savedUser = userRepository.save(createdUser);

        //Generate a new token which needs authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Now with the help of this authentication we will generate the JWT token.
        String token = jwtProvider.generateToken(authentication);

        //Now we need to create a new AuthResponse which we can pass
        AuthResponse authResponse =new AuthResponse(token,true);
        System.out.println("token: "+authResponse.getJwt());

        return  ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user){

        System.out.println("LOGIN endpoint hit");

        String userName = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(userName, password);

        //if authentication is retured means its authenticated so... let's create JWT token like we created for Signup
        String token = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String userName, String password) {

        //Check for email if it exists the match password if both are true then authenticate o/w throw error
        UserDetails userDetails = customUserDetails.loadUserByUsername(userName);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username...");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username or Password...");
        }
        //if both usename and password matches then...
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
