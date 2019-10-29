/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.config;

import com.dsl.spring.profile.practice.security.CustomMethodSecurityExpressionHandler;
import com.dsl.spring.profile.practice.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration
{
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler()
    {
        CustomMethodSecurityExpressionHandler customMethodSecurityExpressionHandler = new CustomMethodSecurityExpressionHandler();
        customMethodSecurityExpressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return customMethodSecurityExpressionHandler;
    }
}
