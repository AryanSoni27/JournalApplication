package com.aryan.journalApp.Controller;

import com.aryan.journalApp.Entity.User;
import com.aryan.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public void healthCheck(){
        System.out.println("Server is Running");
    }

    @PostMapping("/create-user")
    public void addNewUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
