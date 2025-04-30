package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.Request.TweetReplyRequest;
import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;

import java.util.List;

public interface TweetService {

    public Tweet createTweet(Tweet req, User user) throws UserException;

    public List<Tweet> findAllTweets();

    public Tweet retweet(Long tweetId, User user) throws UserException, TweetException;

    public Tweet findById(Long tweetId) throws TweetException;

    public void deleteTweetById(Long tweetId, Long userId) throws UserException, TweetException;

    //to remove a retweet by user on a particular tweet
    public Tweet removerFromRetweet(Long tweetId, User user)throws TweetException, UserException;

    public Tweet createReply(TweetReplyRequest req, User user)throws TweetException;

    public List<Tweet> getUserTweets(User user);

    public List<Tweet> findByLikesContainsUser(User user);
}
