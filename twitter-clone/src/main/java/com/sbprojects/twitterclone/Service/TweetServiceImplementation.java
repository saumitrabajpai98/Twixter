package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.Repository.TweetRepository;
import com.sbprojects.twitterclone.Request.TweetReplyRequest;
import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetServiceImplementation implements TweetService{

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Tweet createTweet(Tweet req, User user) throws UserException {

        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setImage(req.getImage());
        tweet.setUser(user);
        tweet.setReply(false);
        tweet.setTweet(true);
        tweet.setVideo(req.getVideo());

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllTweets() {

        return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
    }

    @Override
    public Tweet retweet(Long tweetId, User user) throws UserException, TweetException {

        Tweet tweet = findById(tweetId); //firstly find what tweet it is by Id given

        if(tweet.getRetweetUser().contains(user)){
            tweet.getRetweetUser().remove(user); //if user exists for in retweet and wants to delete its retweet then this works
        }
        else {
            tweet.getRetweetUser().add(user); //o/w simply add user for its retweet
        }
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findById(Long tweetId) throws TweetException {

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(()-> new TweetException("Tweet not found with ID: "+tweetId));
        return tweet;
    }

    @Override
    public void deleteTweetById(Long tweetId, Long userId) throws UserException, TweetException {

        Tweet tweet = findById(tweetId);

        // if the userId recvd from parameters != tweet's userID (who created it)
        if(!userId.equals(tweet.getUser().getId())){
            throw new UserException("Sorry! You can't delete other User's Tweet.");
        }

        tweetRepository.deleteById(tweet.getId());
    }

    @Override
    public Tweet removerFromRetweet(Long tweetId, User user) throws TweetException, UserException {

        //done in retweet section
        return null;
    }

    @Override
    public Tweet createReply(TweetReplyRequest req, User user) throws TweetException {

        Tweet replyFor = findById(req.getTweetId());

        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setImage(req.getImage());
        tweet.setUser(user);
        tweet.setReply(true);
        tweet.setTweet(false);
        tweet.setReplyFor(replyFor );

        Tweet savedReply = tweetRepository.save(tweet); // adding a tweet to DB

        tweet.getReplyTweets().add(savedReply); //
        tweetRepository.save(replyFor);

        return replyFor;

    }

    @Override
    public List<Tweet> getUserTweets(User user){

        return tweetRepository.findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
    }

    @Override
    public List<Tweet> findByLikesContainsUser(User user) {

        return tweetRepository.findByLikesUser_Id(user.getId());
    }
}
