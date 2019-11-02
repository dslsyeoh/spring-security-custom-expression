/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.RoleEntity;
import com.dsl.spring.profile.practice.domain.UserEntity;
import com.dsl.spring.profile.practice.dto.MyUserDetails;
import com.dsl.spring.profile.practice.dto.User;
import com.dsl.spring.profile.practice.repository.RoleRepository;
import com.dsl.spring.profile.practice.repository.UserRepository;
import com.dsl.spring.profile.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceHandler implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        UserEntity toBeCreated = new UserEntity();
        toBeCreated.setUsername(user.getUsername());
        toBeCreated.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity created  = userRepository.save(toBeCreated);

        return fromEntity(created);
    }

    @Override
    public User update(User user)
    {
        UserEntity toBeUpdated = userRepository.findById(user.getId()).orElse(null);
        if(Objects.nonNull(toBeUpdated))
        {
            toBeUpdated.setUsername(user.getUsername());
            if(!passwordEncoder.matches(user.getPassword(), toBeUpdated.getPassword()))
            {
                toBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            List<RoleEntity> roleEntities = user.getRoles().stream().map(roleRepository::findByRole).collect(Collectors.toList());
            toBeUpdated.setRoles(roleEntities);
            UserEntity updated = userRepository.save(toBeUpdated);
            return fromEntity(updated);
        }
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
        return new MyUserDetails(entity);
    }

    private User fromEntity(UserEntity userEntity)
    {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        List<RoleEntity> roleEntities = userEntity.getRoles();
        if(Objects.nonNull(roleEntities))
        {
            List<String> roles = roleEntities.stream().map(RoleEntity::getRole).collect(Collectors.toList());
            user.setRoles(roles);
        }
        return user;
    }
}
