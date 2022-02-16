package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.User;
import com.example.CommunityAppMessanger.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<User>> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    }
}
