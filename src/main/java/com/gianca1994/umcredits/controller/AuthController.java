package com.gianca1994.umcredits.controller;

import com.gianca1994.umcredits.dto.JwtRequest;
import com.gianca1994.umcredits.dto.JwtResponse;
import com.gianca1994.umcredits.dto.UserDTO;
import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.repository.UserRepository;
import com.gianca1994.umcredits.service.JWTUserDetailsService;

import com.gianca1994.umcredits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "activateheroku")
    public ResponseEntity<?> activateHeroku() {
        return new ResponseEntity<>("Heroku activated", HttpStatus.OK);
    }

    public boolean validateActivationCode(String username) {
        User user = userService.getUserProfile(username);
        return user.isActive();
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (validateActivationCode(authenticationRequest.getUsername())) {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return new ResponseEntity<>(
                    "The user is not active, please check your email " +
                            "and make the correct activation.",
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
            );
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
        User checkUser = userRepository.findByUsername(user.getUsername());

        if (checkUser != null) {
            return new ResponseEntity<>("The user is already registered!", HttpStatus.BAD_REQUEST);
        } else {
            User newUser = userDetailsService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/{username}={codeActivation}")
    public ResponseEntity<Object> activateAccount(@PathVariable String username,
                                                  @PathVariable String codeActivation) {

        if (!validateActivationCode(username)) {
            User user = userRepository.findByUsername(username);

            if (Objects.equals(codeActivation, user.getCodeActivation())) {
                user.setActive(true);
                user.setCodeActivation(null);
                userRepository.save(user);
                return new ResponseEntity<>("The user was successfully activated!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Activation code does not match.", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>("The user is already active.", HttpStatus.NOT_FOUND);
    }
}
