package com.sbprojects.twitterclone.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){

        System.out.println("JwtProvider: jwt generatetoken()");

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000) )//will expire after 24hrs
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }

    //a method which will return email when token is passed.
    public String getEmailFromToken(String jwt){
        jwt = jwt.substring((7)); //Bearer = 6+space
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
