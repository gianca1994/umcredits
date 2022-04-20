package com.gianca1994.umcredits.service;

import com.gianca1994.umcredits.jwt.JwtTokenUtil;
import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.repository.SubjectRepository;
import com.gianca1994.umcredits.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
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

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addSubjectToUser(String username, Long code, byte note) {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        Subject subject = subjectRepository.getById(code);
        User oldUser = user.get();

        oldUser.setCredits((short) (oldUser.getCredits() + subject.getCredits()));
        oldUser.setSubjectsApproved((byte) (oldUser.getSubjectsApproved() + 1));

        if (oldUser.getSubjectsApproved() > 1) {
            oldUser.setAverage((oldUser.getAverage() + note) / 2);
        } else {
            oldUser.setAverage(note);
        }

        user.get().getSubjects().add(subjectRepository.getById(code));
        user.get().setAverage(oldUser.getAverage());
        user.get().setCredits(oldUser.getCredits());
        user.get().setSubjectsApproved(oldUser.getSubjectsApproved());

        userRepository.save(user.get());
    }

     */
}