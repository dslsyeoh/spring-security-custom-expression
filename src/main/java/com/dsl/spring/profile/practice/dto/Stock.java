/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import lombok.Data;

@Data
public class Stock
{
    private Long id;
    private String name;
    private int quantity;
    private String description;
}
