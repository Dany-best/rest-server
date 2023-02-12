package com.dany.clinic.controller;

import com.dany.clinic.model.User;
import com.dany.clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("Dany");
        users.add(user);
        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User userDetails) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return repository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        repository.delete(user);
    }
}