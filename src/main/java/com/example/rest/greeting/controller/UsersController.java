package com.example.rest.greeting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// import com.example.rest.greeting.dto.UsersPosts;
import com.example.rest.greeting.entity.Users;
import com.example.rest.greeting.service.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    
    @GetMapping("/get_users")
    public List<Users> getUsers() {
    	return usersService.getAll();
    }
    
    @GetMapping("/get_user/{name}")
    public Users getUser(@PathVariable final String name) {
    	return usersService.getUserByName(name);
    }

    // @GetMapping("/get_user_posts")
    // public List<UsersPosts> getUserPosts() {
    // 	return usersService.getUserPosts();
    // }

}