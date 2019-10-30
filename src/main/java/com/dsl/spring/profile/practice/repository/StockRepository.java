/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.repository;

import com.dsl.spring.profile.practice.domain.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>
{
}
