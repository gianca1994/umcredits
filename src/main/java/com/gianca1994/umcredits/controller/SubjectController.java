package com.gianca1994.umcredits.controller;


import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.service.SubjectService;
import com.gianca1994.umcredits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("api/v1/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String getTokenUser(String token) {
        String jwtToken = token.substring(7);
        return jwtTokenUtil.getUsernameFromToken(jwtToken);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STANDARD')")
    public Object getSubjects(
            @RequestHeader(value = "Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                ArrayList<Subject> subjectsTotals = subjectService.getSubjects();
                User user = userService.getUserProfile(getTokenUser(token));
                ArrayList<Subject> subjectsUserApproved = new ArrayList<>(user.getSubjects());

                subjectsTotals.removeAll(subjectsUserApproved);

                return new ResponseEntity<>(subjectsTotals, HttpStatus.OK);
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

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STANDARD')")
    public Optional<Subject> getSubject(@PathVariable Long id) {
        return this.subjectService.getSubject(id);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Subject saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }
}
