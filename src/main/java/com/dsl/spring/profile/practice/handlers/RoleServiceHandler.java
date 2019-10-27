/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.dto.Role;
import com.dsl.spring.profile.practice.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceHandler implements RoleService
{
    @Override
    public String create(Role role)
    {
        return null;
    }
}
