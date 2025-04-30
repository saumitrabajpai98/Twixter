package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.Repository.UserRepository;
import com.sbprojects.twitterclone.config.JwtProvider;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {

        User user = userRepository.findById(userId).orElseThrow( ()-> new UserException("User not found with ID: "+userId));
        return user;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null)
            throw new UserException(("User not found with email: "+email));
        return user;
    }

    @Override
    public User updateUser(Long userId, User req) throws UserException {

        User existingUser = findUserById(userId);

        if(req.getFullName()!=null){
            existingUser.setFullName(req.getFullName());
        }
        if(req.getFullName()!=null){
            existingUser.setFullName(req.getFullName());
        }
        if(req.getFullName()!=null){
            existingUser.setFullName(req.getFullName());
        }
        if(req.getImage()!=null){
            existingUser.setImage(req.getImage());
        }
        if(req.getBackgroundImage()!=null){
            existingUser.setBackgroundImage(req.getBackgroundImage());
        }
        if(req.getBirthDate()!=null){
            existingUser.setBirthDate(req.getBirthDate());
        }
        if(req.getLocation()!=null){
            existingUser.setLocation(req.getLocation());
        }
        if(req.getBio()!=null){
            existingUser.setBio(req.getBio());
        }
        if(req.getWebsite()!=null){
            existingUser.setWebsite(req.getWebsite());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public User followUser(Long userId, User user) throws UserException {

        User followtoUser = findUserById(userId);

        if(user.getFollowing().contains(followtoUser) && followtoUser.getFollowers().contains(user)){
            user.getFollowing().remove(followtoUser);
            followtoUser.getFollowers().remove(user);
        }
        else{
            user.getFollowing().add(followtoUser);
            followtoUser.getFollowers().add(user);
        }
        userRepository.save(followtoUser);
        userRepository.save(user);
        return followtoUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }
}
