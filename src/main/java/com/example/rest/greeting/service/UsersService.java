package com.example.rest.greeting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.example.rest.greeting.dto.UsersPosts;
// import com.example.rest.greeting.dto.UserPosts;
import com.example.rest.greeting.entity.Users;
import com.example.rest.greeting.repository.UsersRepository;
@Service
public class UsersService {
	@Autowired
	private UsersRepository userRepository;

	public List<Users> getAll(){
		return userRepository.findAll();
	}

	public Users getUserByName(String name) {		
		return userRepository.getUserByNameIgnoreCase(name).orElseThrow(() -> new ResponseStatusException(
			HttpStatus.NOT_FOUND, "User [name=" + name + "] is not found!"));
	}

	public List<UsersPosts> getUserPosts() {
		if (!userRepository.getAllUserPostsIgnoreCase().isEmpty()) {
			return userRepository.getAllUserPostsIgnoreCase();
		}
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User post was found!");
	}

}