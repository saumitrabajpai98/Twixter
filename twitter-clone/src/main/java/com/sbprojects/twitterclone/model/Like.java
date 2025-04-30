package com.sbprojects.twitterclone.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    //Likes can be many but only one user can like it so.
    private User user;

    @ManyToOne
    private Tweet tweet;

    public Like() {
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Tweet getTweet() {
        return this.tweet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Like)) return false;
        final Like other = (Like) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$tweet = this.getTweet();
        final Object other$tweet = other.getTweet();
        if (this$tweet == null ? other$tweet != null : !this$tweet.equals(other$tweet)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Like;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $tweet = this.getTweet();
        result = result * PRIME + ($tweet == null ? 43 : $tweet.hashCode());
        return result;
    }

    public String toString() {
        return "Like(id=" + this.getId() + ", user=" + this.getUser() + ", tweet=" + this.getTweet() + ")";
    }
}
