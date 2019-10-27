/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleEntity> roles;

}
