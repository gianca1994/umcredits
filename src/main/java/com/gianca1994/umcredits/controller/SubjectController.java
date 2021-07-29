package com.gianca1994.umcredits.controller;


import com.gianca1994.umcredits.model.SubjectModel;
import com.gianca1994.umcredits.service.SubjectService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping()
    public ArrayList<SubjectModel> getSubjects() {
        return subjectService.getSubjects();
    }

    @GetMapping("/{id}")
    public Optional<SubjectModel> getSubject(@PathVariable Long id) {
        return this.subjectService.getSubject(id);
    }

    @PostMapping()
    public SubjectModel saveSubject(@RequestBody SubjectModel subject) {
        return this.subjectService.saveSubject(subject);
    }

    @PutMapping("/{id}")
    public SubjectModel updateSubject(@RequestBody SubjectModel newSubject, @PathVariable Long id) {
        return this.subjectService.updateSubject(newSubject, id);
    }

    @DeleteMapping("/{id}")
    public JSONObject deleteSubject(@PathVariable Long id) {
        JSONObject subjectDeleteData = new JSONObject();

        this.subjectService.deleteSubject(id);
        subjectDeleteData.put("Response", "Material code removed: " + id);
        return subjectDeleteData;
    }
}
