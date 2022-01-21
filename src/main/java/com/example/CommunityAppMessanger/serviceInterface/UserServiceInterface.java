package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {
    ResponseEntity<User> saveUser(User user);

    ResponseEntity<List<User>> findAll();

    ResponseEntity<User> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<User> updateUser(Long id, User user);
}
