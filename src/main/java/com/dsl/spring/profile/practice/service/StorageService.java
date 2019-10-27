/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.service;

import com.dsl.spring.profile.practice.dto.Storage;

import java.util.List;

public interface StorageService
{
    List<Storage> list();

    Storage search(Long id);

    String create(Storage storage);
}
