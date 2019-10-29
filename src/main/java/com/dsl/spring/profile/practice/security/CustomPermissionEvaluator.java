/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

public class CustomPermissionEvaluator implements PermissionEvaluator
{
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
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.startsWith(targetType) && authority.contains(permission));
    }
}
