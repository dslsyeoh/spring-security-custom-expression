/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceHandler implements TokenService
{
    @Override
    public String generateToken(User user)
    {
        return "generated token";
    }
}
