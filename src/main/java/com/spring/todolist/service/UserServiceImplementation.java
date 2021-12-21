package com.spring.todolist.service;

import com.spring.todolist.dao.UserRepository;
import com.spring.todolist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService 
{
    @Autowired
    private UserRepository repository;

    @Override
    public String addDetails(User user)
    {
        Boolean status = existUser(user.getUserName());
        if(status){
            return "User already exist";
        }
        String hashPassword = doHashing(user.getPassword());
        user.setPassword(hashPassword);
        repository.save(user);
        return "SignUp Successful";
    }

    @Override
    public boolean existUser(String userName)
    {
        if(repository.findByUsername(userName) != null)
            return true;
        return false;
    }

    public User getUserDetails(String userName)
    {
        User user = repository.findByUsername(userName);
        return user;
    }

    @Override
    public String checkDetails(Map<String, String> loginData)
    {
            String  username = loginData.get(loginData.keySet().toArray()[0]);
            String password = loginData.get(loginData.keySet().toArray()[1]);
            if(existUser(username))
            {
                User user = getUserDetails(username);
                if( doHashing(password).equals(user.getPassword()))
                    return "Login Successful";
                else
                    return "Incorrect Password";
            }
            return "User doesn't exist.SignUp required";
    }

    public static String doHashing(String password)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : resultByteArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
