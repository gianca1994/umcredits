package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.model.AddSubjectToUser;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<User> getUsers() {
        try {
            return userService.getUsers();
        } catch (Exception error) {
            log.error(error);
            return null;
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        try {
            return this.userService.getUser(id);
        } catch (Exception error) {
            log.error(error);
            return Optional.empty();
        }
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        try {
            return this.userService.saveUser(user);
        } catch (Exception error) {
            log.error(error);
            return null;
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted correctly!", HttpStatus.OK);
    }

    @PutMapping("/{username}/addsubject")
    public ResponseEntity<Object> addSubjectToUser(@PathVariable String username,
                                       @RequestBody AddSubjectToUser addSubjectToUser) {

        if (addSubjectToUser.getNote() >= 6) {
            userService.addSubjectToUser(username, addSubjectToUser.getCode(), (byte) addSubjectToUser.getNote());
            return new ResponseEntity<>("Subject successfully added to the user!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The grade must be higher than 6", HttpStatus.NOT_MODIFIED);
        }
    }
}