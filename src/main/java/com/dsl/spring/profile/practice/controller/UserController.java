/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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

    @PostAuthorize("hasPermission(returnObject, 'ADMIN')")
    @GetMapping("/{id}")
    @ResponseBody
    public User search(@PathVariable Long id)
    {
        return userService.search(id);
    }

    @PostMapping
    public User create(@RequestBody User user)
    {
        return userService.create(user);
    }
}
