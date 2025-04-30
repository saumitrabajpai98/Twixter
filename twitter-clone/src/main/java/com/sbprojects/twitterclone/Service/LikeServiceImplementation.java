package com.sbprojects.twitterclone.Service;

import com.sbprojects.twitterclone.Repository.LikeRepository;
import com.sbprojects.twitterclone.Repository.TweetRepository;
import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Like;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation  implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Like likeTweet(Long tweetId, User user) throws UserException, TweetException {

        Like isLikeExists = likeRepository.isLikeExist(user.getId(), tweetId);

        if(isLikeExists!=null){
            likeRepository.deleteById(isLikeExists.getId()); //if like exists then delete the like o/w create one
            return isLikeExists;
        }

        Tweet tweet = tweetService.findById(tweetId);

        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);

        Like savedLike = likeRepository.save(like);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {

        Tweet tweet = tweetService.findById(tweetId);

        List<Like> likes = likeRepository.findByTweetId(tweetId);

        return likes;
    }
}
