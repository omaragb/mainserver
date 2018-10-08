package com.hotelsmonitor.mainserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // HttpRequest  Create a new User and encode his password
    @PostMapping("/createUser")
    public void addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.insert(user);
    }

    // HttpRequest  which performs siign in to the system , in case the informations are true, a response with code 200
    // returned, if some of the information is not true , BAD_REQUEST returns , in case of neither are true FORBIDDEN reponse is returns
    @ResponseBody
    @PostMapping("/signin/{email}")
    public ResponseEntity<String> singIn(@PathVariable String email, @RequestBody String password){
        System.out.println("email : " + email + " password: " + password );
        User user = this.userRepository.findUserByEmail(email);
        if(user != null){
            System.err.println("+++++++++++++++++++++++++++++++++++");

            JSONObject passJson = new JSONObject(password);
            System.out.println(user);
            System.out.println(user.getPassword());
            System.out.println(passJson.get("password").toString());

            if(passJson.get("password").toString().equals(user.getPassword())){
                System.err.println("===================================");
                ObjectMapper mapper = new ObjectMapper();
                String userJson = null;
                try {
                    userJson = mapper.writeValueAsString(user);
                return new ResponseEntity<>(userJson,HttpStatus.OK);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
}
