/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity
{
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String role;

    @Column
    private String description;
}
