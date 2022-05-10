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

    @Autowired
    private MailService mailService;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

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
            Random random = new Random();
            String activationCode = String.valueOf(random.nextInt(999999999) + 1);

            User newUser = new User();
            Role standardRole = roleRepository.findById(1L).get();

            newUser.setUsername(user.getUsername());
            newUser.setPassword(EncryptData.encryptPassword(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setRemainingSubjects((byte) 50);
            newUser.setYearEligibility((byte) 1);
            newUser.setActive(false);
            newUser.setCodeActivation(activationCode);
            newUser.getRoles().add(standardRole);

            mailService.sendMail(user.getEmail(), user.getUsername(), activationCode);

            return userRepository.save(newUser);
        }
        return null;
    }


}
