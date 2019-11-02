/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.service;

import com.dsl.spring.profile.practice.dto.Role;

public interface RoleService
{
    Role create(Role role);

    Role update(Role role);
}
