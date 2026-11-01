package com.example.secureemployeeapp;

import com.example.secureemployeeapp.entity.AppUser;
import com.example.secureemployeeapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // Create the normal user
            if (userRepository.findByUsername("testuser").isEmpty()) {

                AppUser user = new AppUser();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("Test@123"));
                user.setRole("USER");

                userRepository.save(user);

                System.out.println("Test user created successfully!");

            } else {
                System.out.println("Test user already exists.");
            }

            // Create the admin user
            if (userRepository.findByUsername("admin").isEmpty()) {

                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("Admin@123"));
                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println("Admin user created successfully!");

            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}