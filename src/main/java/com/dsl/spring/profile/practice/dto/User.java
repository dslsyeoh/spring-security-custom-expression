/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import lombok.Data;

import java.util.List;

@Data
public class User
{
    private String username;
    private String password;
    private List<String> roles;
}
