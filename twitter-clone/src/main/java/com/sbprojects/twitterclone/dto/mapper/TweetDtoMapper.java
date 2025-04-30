package com.sbprojects.twitterclone.dto.mapper;

import com.sbprojects.twitterclone.dto.TweetDto;
import com.sbprojects.twitterclone.dto.UserDto;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;
import com.sbprojects.twitterclone.util.TweetUtil;

import java.util.ArrayList;
import java.util.List;

public class TweetDtoMapper {

    public static TweetDto toTweetDto(Tweet tweet, User reqUser) {
        UserDto user = UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);
        boolean isRetweeted = TweetUtil.isRetweetedByReqUser(reqUser, tweet);

        List<Long> reTweetUserId = new ArrayList<>();

        for (User user1 : tweet.getRetweetUser()) {
            reTweetUserId.add(user1.getId());
        }

        TweetDto twitDto = new TweetDto();
        twitDto.setId(tweet.getId());
        twitDto.setContent(tweet.getContent());
        twitDto.setCreatedAt(tweet.getCreatedAt());
        twitDto.setImage(tweet.getImage());
        twitDto.setTotalLikes(tweet.getLikes().size());
        twitDto.setTotalReplies(tweet.getReplyTweets().size());
        twitDto.setTotalRetweets(tweet.getRetweetUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetweet(isRetweeted);
        twitDto.setRetweetUserId(reTweetUserId);
        twitDto.setReplyTweets(toTweetDtos(tweet.getReplyTweets(),reqUser));//added
        twitDto.setVideo(twitDto.getVideo());

        return twitDto;
    }

    public static List<TweetDto> toTweetDtos(List<Tweet> twits, User reqUser) {
        List<TweetDto> twitDtos = new ArrayList<>();

        for (Tweet twit : twits) {
            TweetDto twitDto = toReplyTweetDto(twit, reqUser);
            twitDtos.add(twitDto);
        }
        return twitDtos;
    }

    private static TweetDto toReplyTweetDto(Tweet tweet, User reqUser) {

        UserDto user = UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);
        boolean isRetweeted = TweetUtil.isRetweetedByReqUser(reqUser, tweet);

        List<Long> reTweetUserId = new ArrayList<>();

        for (User user1 : tweet.getRetweetUser()) {
            reTweetUserId.add(user1.getId());
        }

        TweetDto twitDto = new TweetDto();
        twitDto.setId(tweet.getId());
        twitDto.setContent(tweet.getContent());
        twitDto.setCreatedAt(tweet.getCreatedAt());
        twitDto.setImage(tweet.getImage());
        twitDto.setTotalLikes(tweet.getLikes().size());
        twitDto.setTotalReplies(tweet.getReplyTweets().size());
        twitDto.setTotalRetweets(tweet.getRetweetUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetweet(isRetweeted);
        twitDto.setRetweetUserId(reTweetUserId);
        twitDto.setVideo(twitDto.getVideo());

        return twitDto;
    }
}
