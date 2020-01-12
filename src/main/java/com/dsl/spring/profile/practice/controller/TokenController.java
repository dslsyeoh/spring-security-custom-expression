/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/token")
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @PostMapping("/generate")
    public String generateToken(@RequestBody User user)
    {
        return tokenService.generateToken(user);
    }
}
