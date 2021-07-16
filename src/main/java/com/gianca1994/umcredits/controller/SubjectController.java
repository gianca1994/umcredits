package com.gianca1994.umcredits.controller;


import com.gianca1994.umcredits.model.SubjectModel;
import com.gianca1994.umcredits.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping()
    public ArrayList<SubjectModel> getSubjects() {
        return subjectService.getSubjects();
    }

    @PostMapping()
    public SubjectModel saveSubject(@RequestBody SubjectModel subject) {
        return this.subjectService.saveSubject(subject);
    }

    @GetMapping("/{id}")
    public Optional<SubjectModel> getSubject(@PathVariable Long id) {
        return this.subjectService.getSubject(id);
    }

    @PutMapping("/{id}")
    public SubjectModel updateSubject(@RequestBody SubjectModel newSubject, @PathVariable Long id) {
        return this.subjectService.updateSubject(newSubject, id);
    }
}
