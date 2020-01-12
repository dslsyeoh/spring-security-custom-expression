/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.controller;

import com.dsl.spring.profile.practice.dto.Stock;
import com.dsl.spring.profile.practice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stocks")
public class StockController
{
    @Autowired
    private StockService stockService;

    @GetMapping("/list")
    public List<Stock> list()
    {
        return stockService.list();
    }

    @GetMapping("/{id}")
    public Stock search(@PathVariable(name = "id") Long id)
    {
        return stockService.search(id);
    }

    @PreAuthorize("hasPermission(#stock.id, 'EDIT_STOCK', 'ADMIN')")
    @PostMapping
    public String create(@RequestBody Stock stock)
    {
        return stockService.create(stock);
    }
}

