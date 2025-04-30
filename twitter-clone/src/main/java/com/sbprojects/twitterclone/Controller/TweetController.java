package com.sbprojects.twitterclone.Controller;

import com.sbprojects.twitterclone.Request.TweetReplyRequest;
import com.sbprojects.twitterclone.Service.TweetService;
import com.sbprojects.twitterclone.Service.UserService;
import com.sbprojects.twitterclone.dto.TweetDto;
import com.sbprojects.twitterclone.dto.mapper.TweetDtoMapper;
import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;
import com.sbprojects.twitterclone.Reposne.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TweetDto> createTweet(@RequestBody Tweet req,
                                                @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        Tweet tweet = tweetService.createTweet(req,user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);

    }

    @PostMapping("/reply")
    public ResponseEntity<TweetDto> replyTweet(@RequestBody TweetReplyRequest req,
                                                @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        Tweet tweet = tweetService.createReply(req,user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);

    }

    @PutMapping("/{tweetId}/retweet")
    public ResponseEntity<TweetDto> retweet(@PathVariable Long tweetId,
                                                @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        Tweet tweet = tweetService.retweet(tweetId, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.OK);

    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDto> findTweetById(@PathVariable Long tweetId,
                                            @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        Tweet tweet = tweetService.retweet(tweetId, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto, HttpStatus.OK);

    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<ApiResponse> deleteTweet(@PathVariable Long tweetId,
                                            @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        tweetService.deleteTweetById(tweetId, user.getId());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Tweet deleted successfully.");
        apiResponse.setStatus(true);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<TweetDto>> getAllTweets(@PathVariable Long userId,
            @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        List<Tweet> tweets = tweetService.findAllTweets();

        List<TweetDto> tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);

        return new ResponseEntity<>(tweetDtos, HttpStatus.OK);

    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TweetDto>> findTweetsLikesContainsUser(@PathVariable Long userId,
                                                       @RequestHeader("Authorization")String jwt) throws UserException, TweetException {

        User user = userService.findUserProfileByJwt(jwt);

        List<Tweet> tweets = tweetService.findByLikesContainsUser(user);

        List<TweetDto> tweetDtos = TweetDtoMapper.toTweetDtos(tweets, user);

        return new ResponseEntity<>(tweetDtos, HttpStatus.OK);

    }
}
