/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler
{
    private AuthenticationTrustResolverImpl trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation)
    {
        CustomMethodSecurityExpressionRoot customMethodSecurityExpressionRoot = new CustomMethodSecurityExpressionRoot(authentication);
        customMethodSecurityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        customMethodSecurityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        customMethodSecurityExpressionRoot.setTrustResolver(trustResolver);
        customMethodSecurityExpressionRoot.setThis(invocation.getThis());
        return customMethodSecurityExpressionRoot;
    }
}
