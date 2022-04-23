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

import java.util.ArrayList;


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
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.deleteById(user.getId());
    }

    public User saveSubjectToUser(String username, SubjectDTO subject) throws Exception {

        User user = userRepository.findByUsername(username);
        Subject subjectAdd = subjectRepository.findById(subject.getCode()).get();

        for (Object subj : user.getSubjects()) {
            if (subj == subjectAdd)
                return user;
        }

        if (subject.getNote() >= 6) {
            user.getSubjects().add(subjectAdd);
            user.setCredits((short) (user.getCredits() + subjectAdd.getCredits()));

            if (user.getSubjectsApproved() > 1) {
                user.setAverage((user.getAverage() + subject.getNote()) / 2);
            } else {
                user.setAverage(subject.getNote());
            }
            user.setSubjectsApproved((byte) (user.getSubjectsApproved() + 1));
            return userRepository.save(user);
        }
        return user;
    }

    public Object setAdminToIdUser(Long id) {
        User user = userRepository.getById(id);
        Role adminRole = roleRepository.findById(2L).get();

        user.getRoles().add(adminRole);

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