package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.Repository.UserRepository;
import com.sbprojects.twitterclone.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if(user == null || user.isLogin_with_google()){
            //so while logging in if userName is not found (is null) or if user loggedIn thru Google then with noraml login User cannot
            //login and it will throw UsernameNotFoundException. Google wala google ke thru login kare
            throw new UsernameNotFoundException("UserName not found with email: "+username);
        }
        List<GrantedAuthority> auths = new ArrayList<>();

        //we are returning with import package bcoz one User is already imported so there would be a conflict.
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auths);
    }
}
