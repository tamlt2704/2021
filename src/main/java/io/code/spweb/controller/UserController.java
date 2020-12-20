package io.code.spweb.controller;

import io.code.spweb.model.User;
import io.code.spweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    private List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    private User getUserById(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("user/{userId}")
    private void deleteUseById(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }

    @PostMapping("/users")
    private int saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getId();
    }

    @PutMapping("/users")
    private User updateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }
}
