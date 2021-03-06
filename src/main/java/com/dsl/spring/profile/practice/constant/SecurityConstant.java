/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.constant;

public interface SecurityConstant
{
    String AUTHORIZATION_HEADER = "Authorization";
    String BEARER = "Bearer ";
    String SECRET_KEY = "secret123";
    String SCOPE = "scope";
    int JWT_TOKEN_VALIDITY = 5 * 60 * 60;
}
