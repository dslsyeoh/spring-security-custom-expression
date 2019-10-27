/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceHandler implements UserService
{
    @Override
    public List<User> list()
    {
        return null;
    }

    @Override
    public String search(Long id)
    {
        return null;
    }

    @Override
    public String create(User user)
    {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return null;
    }
}
