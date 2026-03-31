package com.nnk.springboot.services;

import com.nnk.springboot.repositories.UserRepository;


public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO
}

