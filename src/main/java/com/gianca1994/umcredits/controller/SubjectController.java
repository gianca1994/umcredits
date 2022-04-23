package com.gianca1994.umcredits.controller;


import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STANDARD')")
    public ArrayList<Subject> getSubjects() {
        return subjectService.getSubjects();
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
