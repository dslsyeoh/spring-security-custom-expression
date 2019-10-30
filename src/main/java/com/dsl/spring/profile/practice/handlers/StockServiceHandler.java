/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.handlers;

import com.dsl.spring.profile.practice.domain.StockEntity;
import com.dsl.spring.profile.practice.dto.Stock;
import com.dsl.spring.profile.practice.repository.StockRepository;
import com.dsl.spring.profile.practice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceHandler implements StockService
{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> list()
    {
        return stockRepository.findAll().stream().map(this::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Stock search(Long id)
    {
        return stockRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    @Override
    public String create(Stock stock)
    {
        StockEntity toBeCreated = new StockEntity();
        toBeCreated.setName(stock.getName());
        toBeCreated.setQuantity(stock.getQuantity());
        toBeCreated.setDescription(stock.getDescription());
        StockEntity created = stockRepository.save(toBeCreated);
        return String.format("Created stock %d: %s", created.getId(), created.getName());
    }

    private Stock fromEntity(StockEntity entity)
    {
        Stock stock = new Stock();
        stock.setId(entity.getId());
        stock.setName(entity.getName());
        stock.setQuantity(entity.getQuantity());
        stock.setDescription(entity.getDescription());
        return stock;
    }
}
