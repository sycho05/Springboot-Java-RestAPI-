package com.example.rest.greeting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.rest.greeting.entity.Posts;
import com.example.rest.greeting.repository.PostsJpaRepository;

@Service
public class PostsService {
	@Autowired
	private PostsJpaRepository postsJpaRepository;
		
	public List<Posts> getPosts(){		
		return postsJpaRepository.findAll(Sort.by("id").descending());
	}

	public Posts getPost(String slug) {
		return postsJpaRepository.findBySlug(slug).get();
	}
	public List<Posts> getPostsWithBody(String body) {
		return postsJpaRepository.findAllByBodyContainsIgnoreCase(body);
	}
	public List<Posts> getPosts(Long userid){	
		//SELECT * FROM posts
//		return postsJpaRepository.findAll();
		return postsJpaRepository.findByUserid(userid);
	}

	public Posts createPost(Posts posts) {
		Optional<Posts> postOptional = postsJpaRepository.findByTitle(posts.getTitle());
		
		if (postOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Same Title Available");
		}
		else {
			//INSERT INTO posts VALUES (posts.slug, post.title, post.body)
	        postsJpaRepository.save(posts);
	        // return postRepository.findBySlug(posts.getSlug()).get();
	        throw new ResponseStatusException(HttpStatus.OK, "Post successfully created!");
		}
	}

    public List<Posts> deletePost(Long id){
        postsJpaRepository.deleteById(id);
        return postsJpaRepository.findAll();
    }

	public boolean deletePostBySlug(String slug){
		if (postsJpaRepository.findBySlug(slug).isPresent()){
			Posts postBySlug = postsJpaRepository.findBySlug(slug).get();

			postsJpaRepository.deleteById(postBySlug.getId());

			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean deletePostByTitle(String title){
		if (postsJpaRepository.findByTitle(title).isPresent()){
			Posts postByTitle = postsJpaRepository.findByTitle(title).get();

			postsJpaRepository.deleteById(postByTitle.getId());

			return true;
		}
		else {
			return false;
		}
		
	}

	public Posts editPost(Posts posts, Long id) {
		Optional<Posts> postOptional = postsJpaRepository.findById(id);
		
		if (postOptional.isPresent()) {
			postsJpaRepository.save(posts);
			// return postOptional.get();
			throw new ResponseStatusException(
				HttpStatus.OK, "Sukses"
			);
		}
		else {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Post [id=" + posts.getId() + "] is not found!");
		}


	}

}

	
	

