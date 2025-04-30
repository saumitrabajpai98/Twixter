package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Like;
import com.sbprojects.twitterclone.model.User;

import java.util.List;

public interface LikeService {

    public Like likeTweet(Long tweetId, User user) throws UserException, TweetException;

    public List<Like> getAllLikes(Long tweetId)throws TweetException;
}
