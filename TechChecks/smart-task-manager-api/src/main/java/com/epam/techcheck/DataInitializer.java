package com.epam.techcheck;

import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.UserRole;
import com.epam.techcheck.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String[] args) {
        if(userRepository.countByRole(UserRole.ADMIN) == 0) {
            User admin = new User(
              "admin",
              "admin@gmail.com",
                    passwordEncoder.encode("Admin@123"),
                    UserRole.ADMIN
            );

            userRepository.save(admin);
        }
    }
}
