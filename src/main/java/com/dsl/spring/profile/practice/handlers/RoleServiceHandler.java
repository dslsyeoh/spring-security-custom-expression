/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.PermissionEntity;
import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.Role;
import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.repository.PermissionRepository;
import com.dsl.spring.profile.practice.repository.RoleRepository;
import com.dsl.spring.profile.practice.repository.UserRepository;
import com.dsl.spring.profile.practice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceHandler implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role create(Role role)
    {
        RoleEntity entity = new RoleEntity();
        entity.setRole(role.getUserRole());
        entity.setDescription(role.getDescription());

        RoleEntity created = roleRepository.save(entity);

        return fromEntity(created);
    }

    @Override
    public Role update(Role role)
    {
        RoleEntity toBeUpdated = roleRepository.findById(role.getId()).orElse(null);
        if(Objects.nonNull(toBeUpdated))
        {
            List<PermissionEntity> permissionEntities = role.getPermissions().stream().map(permissionRepository::findByName).collect(Collectors.toList());
            toBeUpdated.setPermissions(permissionEntities);

            RoleEntity updated = roleRepository.save(toBeUpdated);
            return fromEntity(updated);
        }
        return null;
    }

    private Role fromEntity(RoleEntity entity)
    {
        Role role = new Role();
        role.setId(entity.getId());
        role.setUserRole(entity.getRole());
        role.setDescription(entity.getDescription());
        if(Objects.nonNull(entity.getPermissions()))
        {
            List<String> permissions = entity.getPermissions().stream().map(PermissionEntity::getName).collect(Collectors.toList());
            role.setPermissions(permissions);
        }
        return role;
    }
}
