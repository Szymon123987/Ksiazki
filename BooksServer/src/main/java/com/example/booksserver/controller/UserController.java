package com.example.booksserver.controller;

import com.example.booksserver.model.user.Status;
import com.example.booksserver.model.user.User;
import com.example.booksserver.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    @PostMapping("register/")
    public Status registerUser(@RequestBody User newUser) {


        return userDao.save(newUser);
    }
    @PostMapping("login/")
    public Status loginUser(@RequestBody User user) {
        return  userDao.login(user);
    }

    @GetMapping("find/{username}")
    public User find(@PathVariable("username") String username)
    {
        return userDao.findByUsername(username);
    }

}
