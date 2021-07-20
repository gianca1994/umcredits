package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.model.UserModel;
import com.gianca1994.umcredits.service.UserService;
import net.minidev.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        try {
            return userService.getUsers();
        } catch (Exception error) {
            log.error(error);
            return null;
        }
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getUser(@PathVariable Long id) {
        try {
            return this.userService.getUser(id);
        } catch (Exception error) {
            log.error(error);
            return Optional.empty();
        }
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) {
        try {
            return this.userService.saveUser(user);
        } catch (Exception error) {
            log.error(error);
            return null;
        }
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@RequestBody UserModel newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public JSONObject deleteUser(@PathVariable Long id) {
        JSONObject userDeleteData = new JSONObject();

        userService.deleteUser(id);
        userDeleteData.put("Response", "Deleted User ID: " + id);
        return userDeleteData;
    }

    @PutMapping("/{id}/addsubject/{code}")
    public JSONObject addSubjectToUser(@PathVariable Long id, @PathVariable Long code) {
        JSONObject userDeleteData = new JSONObject();

        userService.addSubjectToUser(id, code);
        userDeleteData.put("Response", "Matter with code: " + code + ", was assigned to the user with the ID: " + id);
        return userDeleteData;
    }
}