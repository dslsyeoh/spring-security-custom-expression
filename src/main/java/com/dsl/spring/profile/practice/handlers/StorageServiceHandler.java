/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.dto.Storage;
import com.dsl.spring.profile.practice.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceHandler implements StorageService
{

    @Override
    public List<Storage> list()
    {
        return null;
    }

    @Override
    public Storage search(Long id)
    {
        return null;
    }

    @Override
    public String create(Storage storage)
    {
        return null;
    }
}
