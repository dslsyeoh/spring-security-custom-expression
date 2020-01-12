/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import lombok.Data;

import java.util.List;

@Data
public class User
{
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
}
