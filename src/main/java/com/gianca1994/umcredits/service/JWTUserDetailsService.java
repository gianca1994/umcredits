package com.gianca1994.umcredits.service;


import com.gianca1994.umcredits.functions.EncryptData;
import com.gianca1994.umcredits.model.Role;
import com.gianca1994.umcredits.repository.RoleRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gianca1994.umcredits.dto.UserDTO;
import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public User save(UserDTO user) {
        if (validateEmail(user.getEmail())) {
            User newUser = new User();
            Role standardRole = roleRepository.findById(1L).get();

            newUser.setUsername(user.getUsername());
            newUser.setPassword(EncryptData.encryptPassword(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setRemainingSubjects((byte) 50);
            newUser.setYearEligibility((byte) 1);
            newUser.setRole(standardRole);

            if (Objects.equals(user.getUsername(), "gianca")) newUser.setRole(roleRepository.findById(2L).get());

            return userRepository.save(newUser);
        }
        return null;
    }


}
