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
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceHandler implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role)
    {
        RoleEntity entity = new RoleEntity();
        entity.setRole(role.getUserRole());
        entity.setDescription(role.getDescription());

        RoleEntity created = roleRepository.save(entity);

        return fromEntity(created);
    }

    private Role fromEntity(RoleEntity entity)
    {
        Role role = new Role();
        role.setUserRole(entity.getRole());
        role.setDescription(entity.getDescription());
        return role;
    }
}
