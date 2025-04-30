package com.sbprojects.twitterclone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    //One user can create many tweets but a tweet will only be created by a single USer.
    private User user;

    private String content;
    private String image;
    private String video;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    //if a tweet is deleted all likes related to it will be deleted, updated etc.
    private List<Like> likes = new ArrayList<>();

    @OneToMany
    private List<Tweet> replyTweets = new ArrayList<>();

    @ManyToMany
    private List<User> retweetUser = new ArrayList<>();

    @ManyToOne
    private Tweet replyFor;

    private boolean isReply; //T/F
    private boolean isTweet; //to check if its Tweet or a reply

    private LocalDateTime createdAt;

}
