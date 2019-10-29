/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.dto;

import com.dsl.spring.profile.practice.domain.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails
{
    private UserEntity userEntity;

    public MyUserDetails(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRole()))).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity getUserEntity()
    {
        return userEntity;
    }
}
