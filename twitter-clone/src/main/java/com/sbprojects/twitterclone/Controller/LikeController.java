package com.sbprojects.twitterclone.Controller;

import com.sbprojects.twitterclone.Service.LikeService;
import com.sbprojects.twitterclone.Service.UserService;
import com.sbprojects.twitterclone.dto.LikeDto;
import com.sbprojects.twitterclone.dto.TweetDto;
import com.sbprojects.twitterclone.dto.mapper.LikeDtoMapper;
import com.sbprojects.twitterclone.exception.TweetException;
import com.sbprojects.twitterclone.exception.UserException;
import com.sbprojects.twitterclone.model.Like;
import com.sbprojects.twitterclone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    UserService userService;

    @Autowired
    LikeService likeService;

    @PostMapping("{tweetId}/likes")
    public ResponseEntity<LikeDto> likeTweet(@PathVariable Long tweetId,
                                             @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = new User();
        Like like = new Like();

        LikeDto likeDto = LikeDtoMapper.toLikeDto(like, user);

        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
    }

    @PostMapping("/twit/{twiId}")
    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long twiId,
                                                     @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Like> likes = likeService.getAllLikes(twiId);

        List<LikeDto> likeDto = LikeDtoMapper.toLikeDtos(likes, user);

        return new ResponseEntity<>(likeDto, HttpStatus.CREATED);
    }
}
