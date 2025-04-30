package com.sbprojects.twitterclone.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
    private String image;
    private String location;
    private String website;
    private String birthDate;
    private String backgroundImage;
    private String mobile;
    private String bio;
    private boolean login_with_google;

    private boolean req_user;

    private List<UserDto> followers = new ArrayList<>();
    private List<UserDto> following = new ArrayList<>();

    private boolean followed;
    private boolean isVerified;
}
