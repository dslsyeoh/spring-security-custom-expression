/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list()
    {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User search(@PathVariable(name = "id") Long id)
    {
        return userService.search(id);
    }

    @PostMapping
    public String create(@RequestBody User user)
    {
        return userService.create(user);
    }
}
