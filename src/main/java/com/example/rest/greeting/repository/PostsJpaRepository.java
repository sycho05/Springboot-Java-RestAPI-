package com.example.rest.greeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

// import java.util.Optional;

import com.example.rest.greeting.entity.Posts;

@Component
public interface PostsJpaRepository extends JpaRepository<Posts, Long>{

	Optional<Posts> findBySlug(String slug);

    Optional<Posts> findByTitle(String title);

    @Transactional
    void deleteByTitle(String title);

    List<Posts> findAllByBodyContainsIgnoreCase(String body);
    
}
