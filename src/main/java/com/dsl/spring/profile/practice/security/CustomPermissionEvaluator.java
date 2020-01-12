/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.security;

import com.dsl.spring.profile.practice.domain.PermissionEntity;
import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.repository.RoleRepository;
import com.dsl.spring.profile.practice.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission)
    {
        if(Objects.isNull(target))
        {
            return false;
        }
        String targetType = target.getClass().getSimpleName().toUpperCase();
        return hasPermission(authentication, null, targetType, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)
    {
        if(Objects.isNull(authentication) || Objects.isNull(targetType) || !(permission instanceof String))
        {
            return false;
        }
        return hasPermission(authentication, targetType, permission.toString().toUpperCase());
    }

    private boolean hasPermission(Authentication authentication, String targetType, String permission)
    {
        List<String> permissions = new ArrayList<>();
        RoleEntity roleEntity = roleRepository.findByRole(permission);
        if(Objects.nonNull(roleEntity))
        {
            permissions = roleEntity.getPermissions().stream().map(PermissionEntity::getName).collect(Collectors.toList());
        }

        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        for(String authority : authorities)
        {
            if(permissions.contains(targetType) && authority.contains(permission))
            {
                    return true;
            }
        }
        return false;
    }
}
