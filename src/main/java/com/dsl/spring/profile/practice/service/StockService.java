/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.service;

import com.dsl.spring.profile.practice.dto.Stock;

import java.util.List;

public interface StockService
{
    List<Stock> list();

    Stock search(Long id);

    String create(Stock stock);
}
