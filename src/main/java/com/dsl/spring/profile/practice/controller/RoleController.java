/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.Role;
import com.dsl.spring.profile.practice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
public class RoleController
{
    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role create(@RequestBody Role role)
    {
        return roleService.create(role);
    }

    @PutMapping
    public Role update(@RequestBody Role role) { return roleService.update(role); }
}
