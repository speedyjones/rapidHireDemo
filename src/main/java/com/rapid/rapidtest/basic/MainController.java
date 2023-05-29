package com.rapid.rapidtest.basic;

import com.rapid.rapidtest.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    private String login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid User Details");
        }
    }

    @PostMapping("/register")
    private String register(@RequestBody Users users){
        return usersService.register(users);
    }

    @GetMapping("/getUsers")
    private List<Users> getUsers(){
        return usersService.getUsers();
    }


}
