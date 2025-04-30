package com.sbprojects.twitterclone.Repository;

import com.sbprojects.twitterclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //when we xtend JPARepo then we get some exising methods like to add, delete, update user.JpaRepository<User, ID>
    //we can also give SQLQuery here if we don't want to use methods. Using @Query annotation
    public User findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.fullName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query")String query);
}
