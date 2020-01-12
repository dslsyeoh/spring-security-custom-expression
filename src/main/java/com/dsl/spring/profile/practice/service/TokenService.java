/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.service;

import com.dsl.spring.profile.practice.dto.User;

public interface TokenService
{
    String generateToken(User user);
}
