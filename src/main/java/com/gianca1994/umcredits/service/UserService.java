package com.gianca1994.umcredits.service;

import com.gianca1994.umcredits.model.UserModel;
import com.gianca1994.umcredits.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ArrayList<UserModel> getUser() {
        return (ArrayList<UserModel>) this.userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        return this.userRepository.save(user);
    }
}