package com.hotelsmonitor.mainserver;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.GetUserRequest;
import com.amazonaws.services.identitymanagement.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsersController {
    AmazonIdentityManagement amazonIdentityManagement = AmazonIdentityManagementClientBuilder.defaultClient();

//    @PostMapping("/singIn")
//    public void signIn(@PathVariable String userName, @PathVariable String password){
//       User user = amazonIdentityManagement.getUser(new GetUserRequest().withUserName(userName)).getUser();
//        Signin
//    }
}
