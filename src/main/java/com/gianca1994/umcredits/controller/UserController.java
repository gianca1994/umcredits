package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.dto.SubjectDTO;
import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String getTokenUser(String token) {
        String jwtToken = token.substring(7);
        return jwtTokenUtil.getUsernameFromToken(jwtToken);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Object> getAllUsers(@RequestHeader(value = "Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        userService.getUsers(),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        "The token is required to perform this action.",
                        HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Object> deleteUser(@RequestHeader(value = "Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                userService.deleteUser(getTokenUser(token));
                return new ResponseEntity<>("User deleted correctly!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        "The token is required to perform this action.",
                        HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("me")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Object> myProfile(@RequestHeader(value = "Authorization") String token) {

        try {
            if (token != null && token.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        userService.getUserProfile(getTokenUser(token)),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        "The token is required to perform this action.",
                        HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("approve")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Object> approveSubject(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody SubjectDTO subject) {

        try {
            if (token != null && token.startsWith("Bearer ")) {

                return new ResponseEntity<>(
                        userService.saveSubjectToUser(
                                getTokenUser(token),
                                subject
                        ),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        "The token is required to perform this action.",
                        HttpStatus.NO_CONTENT);
            }
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }
}

    /*
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

     */