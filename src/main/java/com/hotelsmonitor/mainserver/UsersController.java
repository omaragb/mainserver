package com.hotelsmonitor.mainserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class UsersController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/createUser")
    public void addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.insert(user);
    }
    @PostMapping("/signin")
    public void singIn(@RequestBody User user){
        passwordEncoder.matches(user.getPassword(),this.userRepository.findUserByName(user.getName()).getPassword());
    }
}
