package com.spring.todolist.controller;

import com.spring.todolist.model.User;
import com.spring.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class LoginController
{
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<String> addDetails(@RequestBody User user)
    {
       String message = userService.addDetails(user);
        return new ResponseEntity<String>(message,HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> loginCredentials(@RequestBody Map<String, String> loginData)
    {
        String message = userService.checkDetails(loginData);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
