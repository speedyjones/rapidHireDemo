package com.rapid.rapidtest.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersInfoDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByUsername(username);
        return users.map(UsersDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found " + username));
    }
}
