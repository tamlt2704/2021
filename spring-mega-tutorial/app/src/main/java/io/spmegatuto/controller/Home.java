package io.spmegatuto.controller;

import io.spmegatuto.model.User;
import io.spmegatuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "Hello world";
    }

    @RequestMapping("/users")
    public List<User> users() {
        return userService.findAllUSers();
    }
}