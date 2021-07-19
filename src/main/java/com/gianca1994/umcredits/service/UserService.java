package com.gianca1994.umcredits.service;

import com.gianca1994.umcredits.model.UserModel;
import com.gianca1994.umcredits.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

        if (validateEmail(user.getEmail())) {
            UserModel newUser = new UserModel();

            newUser.setEmail(user.getEmail());
            newUser.setPassword(encryptPassword(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());

            return userRepository.save(newUser);
        }
        return null;
    }

    public UserModel updateUser(UserModel newUser, Long id) {

        return userRepository.findById(id).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(encryptPassword(newUser.getPassword()));

            return userRepository.save(user);

        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public Optional<UserModel> addSubjectToUser(Long id, Long code) {

        return userRepository.findById(id).map(user -> {
            user.setEmail(user.getEmail());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPassword(user.getPassword());

            return userRepository.save(user);
        });
    }
}