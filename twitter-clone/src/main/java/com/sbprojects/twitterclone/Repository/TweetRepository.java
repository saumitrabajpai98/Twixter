package com.sbprojects.twitterclone.Repository;

import com.sbprojects.twitterclone.model.Tweet;
import com.sbprojects.twitterclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

    //this find if the tweet is an actual tweet or a reply of another tweet.
    public List<Tweet> findAllByIsTweetTrueOrderByCreatedAtDesc();

    public List<Tweet> findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user, Long userId);


    public List<Tweet> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT t FROM Tweet t JOIN t.likes l WHERE l.user.id=:userId")
    //give all Tweets which are liked by this particular user.
    public List<Tweet> findByLikesUser_Id(Long userId);
}
