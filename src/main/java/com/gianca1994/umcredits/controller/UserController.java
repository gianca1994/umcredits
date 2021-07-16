package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.model.UserModel;
import com.gianca1994.umcredits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }
}