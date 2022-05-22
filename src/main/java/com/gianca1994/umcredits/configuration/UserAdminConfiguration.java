package com.gianca1994.umcredits.configuration;

import com.gianca1994.umcredits.model.User;
import com.gianca1994.umcredits.repository.RoleRepository;
import com.gianca1994.umcredits.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAdminConfiguration {
    @Bean
    public CommandLineRunner autoRegisterAdmin(RoleRepository roleRepository,
            UserRepository userRepository) {
        return args -> {
            User userAdmin = new User();

            userAdmin.setUsername("gianca");
            userAdmin.setEmail("gianca9405@gmail.com");
            userAdmin.setFirstName("Giancarlo");
            userAdmin.setLastName("Galvarini Hauser");
            userAdmin.setPassword(BCrypt.hashpw("test", BCrypt.gensalt(12)));
            userAdmin.getRoles().add(roleRepository.findById(2L).get());
            userAdmin.setRemainingSubjects((byte) 50);
            userAdmin.setYearEligibility((byte) 1);
            userAdmin.setActive(true);

            userRepository.save(userAdmin);
        };
    }
}
