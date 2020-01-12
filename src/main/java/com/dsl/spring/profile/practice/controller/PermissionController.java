/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.Permission;
import com.dsl.spring.profile.practice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/permissions")
public class PermissionController
{
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public Permission create(@RequestBody Permission permission)
    {
        return permissionService.create(permission);
    }
}
