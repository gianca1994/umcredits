package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.dto.SubjectDTO;
import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/users")
public class UserController {

    // private final Log log = LogFactory.getLog(getClass());

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

    @GetMapping("me")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Object> myProfile(@RequestHeader(value = "Authorization") String token) {

        try {
            if (token != null && token.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        userService.getUser(getTokenUser(token)),
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
     */