/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import lombok.Data;

@Data
public class Role
{
    private Long userId;
    private String userRole;
    private String description;
}
