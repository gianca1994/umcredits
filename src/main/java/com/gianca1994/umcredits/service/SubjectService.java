package com.gianca1994.umcredits.service;


import com.gianca1994.umcredits.model.SubjectModel;
import com.gianca1994.umcredits.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public ArrayList<SubjectModel> getSubjects() {
        return (ArrayList<SubjectModel>) this.subjectRepository.findAll();
    }

    public Optional<SubjectModel> getSubject(Long code) {
        return subjectRepository.findById(code);
    }

    public SubjectModel saveSubject(SubjectModel subject) {
        return this.subjectRepository.save(subject);
    }

    public SubjectModel updateSubject(SubjectModel newSubject, Long code) {

        return subjectRepository.findById(code).map(subject -> {
            subject.setName(newSubject.getName());
            return subjectRepository.save(subject);

        }).orElseGet(() -> {

            newSubject.setCode(code);
            return subjectRepository.save(newSubject);
        });
    }

    public void deleteSubject(Long code) {
        this.subjectRepository.deleteById(code);
    }
}
