package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.model.AddSubjectToUser;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private boolean userCorrespondToUserRequest(
            String requestTokenHeader, String username) {

        String usernameRequest = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            usernameRequest = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        return Objects.equals(usernameRequest, username);
    }

    @GetMapping("me")
    public Object myProfile(HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader("Authorization");
        String usernameRequest = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            usernameRequest = jwtTokenUtil.getUsernameFromToken(jwtToken);
            return userService.getUser(usernameRequest);
        }
        return null;
    }

    @GetMapping()
    public ArrayList<User> getUsers() {
        try {
            return userService.getUsers();
        } catch (Exception error) {
            log.error(error);
            return null;
        }
    }

    @GetMapping("/{username}")
    public Object getUser(HttpServletRequest request, @PathVariable String username) {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");

            if (userCorrespondToUserRequest(requestTokenHeader, username)) {
                return userService.getUser(username);
            } else {
                return null;
            }
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
    public ResponseEntity<Object> addSubjectToUser(
            HttpServletRequest request,
            @PathVariable String username,
            @RequestBody AddSubjectToUser addSubjectToUser) {

        try {
            final String requestTokenHeader = request.getHeader("Authorization");

            if (userCorrespondToUserRequest(requestTokenHeader, username)) {
                if (addSubjectToUser.getNote() >= 6) {
                    userService.addSubjectToUser(username, addSubjectToUser.getCode(), (byte) addSubjectToUser.getNote());
                    return new ResponseEntity<>("Subject successfully added to the user!", HttpStatus.OK);
                }
               return new ResponseEntity<>("The grade must be higher than 6", HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>("It is not possible to add subjects to another user.", HttpStatus.BAD_REQUEST);
        } catch (Exception error) {
            log.error(error);
        }
        return null;
    }

}