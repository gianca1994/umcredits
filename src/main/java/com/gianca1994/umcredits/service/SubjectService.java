package com.gianca1994.umcredits.service;


import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public ArrayList<Subject> getSubjects() {
        return (ArrayList<Subject>) this.subjectRepository.findAll();
    }

    public Optional<Subject> getSubject(Long code) {
        return subjectRepository.findById(code);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long code) {
        this.subjectRepository.deleteById(code);
    }
}
