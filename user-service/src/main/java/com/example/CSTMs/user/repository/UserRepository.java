package com.example.CSTMs.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.CSTMs.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    // Find user by email (for login & duplicate check)
    Optional<User> findByEmail(String email);

    // Find user by username (for profile searches)
    Optional<User> findByUserName(String userName);
    
    // Get a list of users based on role (Admin, Agent, User)
    List<User> findByRole(int role);
    
}
