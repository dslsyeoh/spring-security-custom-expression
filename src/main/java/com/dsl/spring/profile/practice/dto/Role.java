/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import lombok.Data;

import java.util.List;

@Data
public class Role
{
    private String userRole;
    private String description;
    private List<String> users;
}
