package com.example.rest.greeting.repository;

import java.util.Optional;

import com.example.rest.greeting.entity.UserRole;
import com.example.rest.greeting.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<Users> findByUSERID(Long userid);

}
