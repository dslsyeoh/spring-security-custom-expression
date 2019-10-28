/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.repository.RoleRepository;
import com.dsl.spring.profile.practice.repository.UserRepository;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceHandler implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> list()
    {
        return userRepository.findAll().stream().map(this::fromEntity).collect(Collectors.toList());
    }

    @Override
    public User search(Long id)
    {
        return userRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    @Override
    public User create(User user)
    {
        List<RoleEntity> roleEntities = user.getRoles().stream().map(roleRepository::findByRole).collect(Collectors.toList());
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setRoles(roleEntities);
        userRepository.save(entity);
        return fromEntity(entity);
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

    private User fromEntity(UserEntity userEntity)
    {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        List<String> roles = userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toList());
        user.setRoles(roles);
        return user;
    }
}
