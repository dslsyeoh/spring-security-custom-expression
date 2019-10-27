/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "storage")
public class StorageEntity
{
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column
    private String description;
}
