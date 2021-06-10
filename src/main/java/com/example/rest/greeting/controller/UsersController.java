package com.example.rest.greeting.controller;

import java.util.List;

// import com.example.rest.greeting.dto.UsersPosts;
import com.example.rest.greeting.entity.UserRole;
import com.example.rest.greeting.entity.UserRoleView;
import com.example.rest.greeting.entity.Users;
import com.example.rest.greeting.service.UsersService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
	
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    
    @GetMapping("/get")
    public List<Users> getUsers() {
    	return usersService.getAll();
    }

    @GetMapping("/get_id/{id}")
    public Users getUsersId(@PathVariable final Long id){
        return usersService.getUsersById(id);
    }
    @GetMapping("/get/{name}")
    public Users getUser(@PathVariable final String name) {
    	return usersService.getUserByName(name);
    }
    
    @GetMapping("/get_by_email/{email}")
    public Users getUserByEmail(@PathVariable final String email) {
    	return usersService.getUserByEmail(email);
    }
    @DeleteMapping("/delete_users/{id}")
    public void delete_user(@PathVariable final Long id) {
        usersService.deleteUser(id);
    }

    @GetMapping("/getRole")
    public List<UserRoleView> getAllUserRole(){
        return usersService.getAllUserRole();
    }

    @GetMapping("/getrole_by_userid/{userId}")
    public UserRoleView getUserRoleByUserId(@PathVariable final Long userId){
        return usersService.getUserRoleByUserId(userId);
    }

    @GetMapping("/getrole_by_email/{email}")
    public UserRoleView getUserRoleByEmail(@PathVariable final String email){
        return usersService.getUserRoleByEmail(email);
    }

    @PutMapping("/edit_role/{roleId}")
    public void ediRole(@RequestBody final UserRole userRole, @PathVariable final Long roleId){
    	usersService.editRole(userRole, roleId);
    }

    @DeleteMapping("/delete/{userid}")
    public void delete(@PathVariable final Long userid) {
        usersService.deleteUser(userid);
    }


     @PostMapping("/create")
    public void create(@RequestBody final Users users){
    	usersService.createUser(users);
    }

    @PostMapping("/create_role")
    public void create(@RequestBody final UserRole userRole){
        usersService.createUserRole(userRole);
    }

    @DeleteMapping("/delete_role/{userRoleId}")
    public void deleteUserRole(@PathVariable final Long userRoleId){
    	usersService.deleteUserRole(userRoleId);
    }








}
