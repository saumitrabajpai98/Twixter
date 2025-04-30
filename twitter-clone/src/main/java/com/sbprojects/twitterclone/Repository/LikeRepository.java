package com.sbprojects.twitterclone.Repository;

import com.sbprojects.twitterclone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    //if any user liked the post the it will return like else it will return false
    @Query("SELECT l FROM Like l WHERE l.user.id=:userId AND l.tweet.id=:tweetId")
    public Like isLikeExist(@Param("userId")Long userId, @Param("tweetId") Long tweetId);

    @Query("SELECT l FROM Like l WHERE l.tweet.id=:tweetId")
    public List<Like> findByTweetId(@Param("tweetId") Long tweetId);

    //the JPARepo<1,2> u are extending has <1,2> things ==> 1. Model Class, 2. id type which is there in model class.
    //then JPARepo<> gives some pre-existing functions like save, delete, update, findByID etc so u don't have to do manually.

    //adding manual(methods acc to need)

}
