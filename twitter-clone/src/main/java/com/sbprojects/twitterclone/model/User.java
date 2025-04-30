package com.sbprojects.twitterclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//import lombok.Data;


//@Entity for SQL Relational DB Mapping (Map POJO's to SQL DB's), @Data for Lombot Getter, Setter, ToSring methods
// By default ClassName is taken as Table name and FieldName as Column name if u want togive explicitly then use @Table & @Column
@Data
@Entity
@Table(name ="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile; //Stirng bcoz of +91
    private String image;
    private String backgroundImage;
    private String bio;
    private boolean req_user; //whenever we find user locally by JSON Web Token and the user we find by ID is same or not
    private boolean login_with_google; //if login with Google then no password will be there

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //@JSONIgnore: whenevr we fetch data of user ignore this part.
    //@OneToMany: One user can have many Tweets so. Whenever a Tweet is created then we will provide User to which Tweet will be mapped. Also no new Table will be created.
    //            CascadeType.ALL means whenever a User will be deleted all the related Tweets, Posts and Likes will also be deleted.
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //Whenever Tweet will be deleted all the likes related to it will also be deleted.
    private List<Like> likes = new ArrayList<>();

    @Embedded
    //All the feilds of Verification will be embeded into User whenever UserModel is created.
    private Verification verification;

    @JsonIgnore
    @ManyToMany
    private List<User> followers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<User> following = new ArrayList<>();
}
