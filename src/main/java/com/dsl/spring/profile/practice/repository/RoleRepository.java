/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.repository;

import com.dsl.spring.profile.practice.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
    RoleEntity findByRole(String role);
}
