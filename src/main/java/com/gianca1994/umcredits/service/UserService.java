package com.gianca1994.umcredits.service;

import com.gianca1994.umcredits.model.SubjectModel;
import com.gianca1994.umcredits.model.UserModel;
import com.gianca1994.umcredits.repository.SubjectRepository;
import com.gianca1994.umcredits.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) this.userRepository.findAll();
    }

    public Optional<UserModel> getUser(Long id) {
        return userRepository.findById(id);
    }

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public UserModel saveUser(UserModel user) {

        if (user.getPassword().length() < 8) {
            return null;
        }

        if (validateEmail(user.getEmail())) {
            UserModel newUser = new UserModel();

            newUser.setEmail(user.getEmail());
            newUser.setPassword(encryptPassword(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());

            return userRepository.save(newUser);
        } else {
            return null;
        }
    }

    public UserModel updateUser(UserModel newUser, Long id) {

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

    public void addSubjectToUser(Long id, Long code) {

        HashSet<SubjectModel> set = new HashSet<>();
        set.add(subjectRepository.getById(code));

        userRepository.findById(id).map(user -> {
            user.setSubjects(set);
            userRepository.save(user);
            return null;
        });
    }
}