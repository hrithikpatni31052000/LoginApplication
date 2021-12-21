package com.spring.todolist.service;

import com.spring.todolist.model.User;

import java.util.Map;

public interface UserService {

    String addDetails(User signUp);
    boolean existUser(String userName);
    String checkDetails(Map<String, String> admin);
}
