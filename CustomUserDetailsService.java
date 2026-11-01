package com.example.secureemployeeapp.service;

import com.example.secureemployeeapp.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.secureemployeeapp.entity.AppUser;

@Service
public  class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    protected CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
     return userRepository;
    }
    public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {
            AppUser appUser=userRepository.findByUsername(username)
                    .orElseThrow(()->
                            new UsernameNotFoundException("usernotfound" + username));

        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();

    }
    }

