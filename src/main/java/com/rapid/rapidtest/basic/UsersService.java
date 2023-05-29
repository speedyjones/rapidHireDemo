package com.rapid.rapidtest.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    public String register(Users users) {
        usersRepository.save(Users.builder()
                .username(users.getUsername())
                .password(passwordEncoder.encode(users.getPassword()))
                .roles(users.getRoles())
                .build());

        return "User Registered";
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
}
