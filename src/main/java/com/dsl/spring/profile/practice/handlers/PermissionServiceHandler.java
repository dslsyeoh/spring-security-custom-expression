/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.PermissionEntity;
import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.Permission;
import com.dsl.spring.profile.practice.dto.Role;
import com.dsl.spring.profile.practice.repository.PermissionRepository;
import com.dsl.spring.profile.practice.repository.RoleRepository;
import com.dsl.spring.profile.practice.repository.UserRepository;
import com.dsl.spring.profile.practice.service.PermissionService;
import com.dsl.spring.profile.practice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceHandler implements PermissionService
{
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(Permission permission)
    {
        PermissionEntity toBeCreated = new PermissionEntity();
        toBeCreated.setName(permission.getName());
        toBeCreated.setDescription(permission.getDescription());

        PermissionEntity created = permissionRepository.save(toBeCreated);
        return fromEntity(created);
    }

    private Permission fromEntity(PermissionEntity entity)
    {
        Permission permission = new Permission();
        permission.setName(entity.getName());
        permission.setDescription(entity.getDescription());
        return permission;
    }
}
