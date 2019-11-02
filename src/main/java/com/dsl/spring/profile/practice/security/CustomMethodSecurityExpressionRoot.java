/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.security;

import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.MyUserDetails;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Objects;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations
{
    private Object filterObject;
    private Object returnObject;
    private Object target;

    CustomMethodSecurityExpressionRoot(Authentication authentication)
    {
        super(authentication);
    }

    @Override
    public void setFilterObject(Object filterObject)
    {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    public void setThis(Object target)
    {
        this.target = target;
    }

    public boolean isAdmin()
    {
        UserEntity userEntity = ((MyUserDetails) this.getPrincipal()).getUserEntity();
        return userEntity.getRoles().stream().map(RoleEntity::getRole).anyMatch(role -> Objects.equals(role, "ADMIN"));
    }
}
