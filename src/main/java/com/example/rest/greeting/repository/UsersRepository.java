package com.example.rest.greeting.repository;

import java.util.List;
import java.util.Optional;

import com.example.rest.greeting.dto.UsersPosts;
import com.example.rest.greeting.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    @Query(value = "SELECT new com.example.rest.greeting.dto.UserPosts(p.body, p.created, p.slug, p.title, u.name) FROM Users u JOIN Posts p ON u.id=p.userid"
            , nativeQuery = true)
    List<UsersPosts> getAllUserPostsIgnoreCase();

    @Query(value = "SELECT * FROM Users u WHERE UPPER(u.name)=UPPER(?1)", nativeQuery = true)
	Optional<Users> getUserByNameIgnoreCase(String name);

    Optional<Users>findByEmail(String email);
}