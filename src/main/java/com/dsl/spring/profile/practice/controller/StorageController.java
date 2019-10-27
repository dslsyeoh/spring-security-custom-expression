/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.Storage;
import com.dsl.spring.profile.practice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/storage")
public class StorageController
{
    @Autowired
    private StorageService storageService;

    @GetMapping("/list")
    public List<Storage> list()
    {
        return storageService.list();
    }

    @GetMapping("/{id}")
    public Storage search(@PathVariable(name = "id") Long id)
    {
        return storageService.search(id);
    }

    @PostMapping
    public String create(@RequestBody Storage storage)
    {
        return storageService.create(storage);
    }
}

