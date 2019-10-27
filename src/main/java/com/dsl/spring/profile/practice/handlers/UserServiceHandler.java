/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.repository.UserRepository;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceHandler implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list()
    {
        return null;
    }

    @Override
    public User search(Long id)
    {
        return null;
    }

    @Override
    public String create(User user)
    {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        UserEntity entity = userRepository.findByUsername(username);
        if(Objects.isNull(entity))
        {
            throw new UsernameNotFoundException("username not found");
        }
        return new org.springframework.security.core.userdetails.User(entity.getUsername(), entity.getPassword(), Collections.emptyList());
    }
}
