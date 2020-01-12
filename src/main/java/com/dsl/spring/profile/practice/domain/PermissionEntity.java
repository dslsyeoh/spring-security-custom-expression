/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "permission")
public class PermissionEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<RoleEntity> roles;
}
