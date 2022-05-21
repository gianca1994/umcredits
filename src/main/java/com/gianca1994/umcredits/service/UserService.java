package com.gianca1994.umcredits.service;

import com.gianca1994.umcredits.dto.SubjectDTO;
import com.gianca1994.umcredits.model.Role;
import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.repository.RoleRepository;
import com.gianca1994.umcredits.repository.SubjectRepository;
import com.gianca1994.umcredits.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public User getUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        float average = 0;

        for (Subject b : user.getSubjects()) {
            average += b.getNote();
        }

        if (user.getSubjectsApproved() > 0)
            user.setAverage(average / user.getSubjectsApproved());
        return user;
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        Role adminRole = roleRepository.findById(2L).get();

        for (Role role : user.getRoles()) {
            if (adminRole.equals(role)) {
                return;
            }
        }
        userRepository.deleteById(user.getId());
    }

    public byte yearEligibilityCalculate(short credits) {
        byte yearEligibility = 1;

        if (credits >= 130) {
            yearEligibility = 5;
        } else if (credits >= 90) {
            yearEligibility = 4;
        } else if (credits >= 38) {
            yearEligibility = 3;
        } else if (credits >= 12) {
            yearEligibility = 2;
        }
        return yearEligibility;
    }

    public User saveSubjectToUser(String username, SubjectDTO subject) {

        User user = userRepository.findByUsername(username);
        Subject subjectAdd = subjectRepository.findById(subject.getCode()).get();

        for (Object subj : user.getSubjects()) {
            if (subj == subjectAdd)
                return user;
        }

        if (subject.getNote() >= 6) {
            subjectAdd.setNote(subject.getNote());
            user.getSubjects().add(subjectAdd);
            user.setCredits((short) (user.getCredits() + subjectAdd.getCredits()));
            user.setSubjectsApproved((byte) (user.getSubjectsApproved() + 1));
            user.setRemainingSubjects((byte) (user.getRemainingSubjects() - 1));
            user.setYearEligibility(yearEligibilityCalculate(user.getCredits()));
            user.setAverage(user.getAverage() + subjectAdd.getNote());

            return userRepository.save(user);
        }
        return user;
    }

    public User deleteSubjectToUser(String username, Long code) {
        User user = userRepository.findByUsername(username);
        Subject subject = subjectRepository.findById(code).get();

        user.setAverage(user.getAverage() - subject.getNote());
        user.setCredits((short) (user.getCredits() - subject.getCredits()));
        user.setSubjectsApproved((byte) (user.getSubjectsApproved() - 1));
        user.setRemainingSubjects((byte) (user.getRemainingSubjects() + 1));

        user.getSubjects().remove(subject);
        userRepository.save(user);
        return user;
    }

    public Object setAdminToIdUser(String adminUserName) {
        User user = userRepository.findByUsername(adminUserName);
        Role moderatorRole = roleRepository.findById(3L).get();
        Role adminRole = roleRepository.findById(2L).get();
        Role standardRole = roleRepository.findById(1L).get();

        for (Role role : user.getRoles()) {
            if (adminRole.equals(role)) {
                return null;
            } else {
                if (moderatorRole.equals(role)) {
                    user.getRoles().remove(moderatorRole);
                    user.getRoles().add(standardRole);
                } else {
                    user.getRoles().remove(standardRole);
                    user.getRoles().add(moderatorRole);
                }
            }
        }

        return userRepository.save(user);
    }

    /*
    public User updateUser(User newUser, Long id) {

        return userRepository.findById(id).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setPassword(encryptPassword(newUser.getPassword()));
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());

            return userRepository.save(user);

        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }
     */
}