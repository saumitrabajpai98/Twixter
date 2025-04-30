package com.sbprojects.twitterclone.util;

import com.sbprojects.twitterclone.model.Like;
import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;

public class TweetUtil {

    public final static boolean isLikedByReqUser(User reqUser, Tweet tweet) {

        //searching in the array of likes of a particular tweet if like of that particular user exists then return true else false
        for (Like like : tweet.getLikes()) {
            if (like.getUser().getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isRetweetedByReqUser(User reqUser, Tweet tweet) {

        for (User user : tweet.getRetweetUser()) {
            if (user.getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }

}
