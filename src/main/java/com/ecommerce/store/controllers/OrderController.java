package com.ecommerce.store.controllers;

import com.ecommerce.store.dtos.OrderDto;
import com.ecommerce.store.mappers.OrderMapper;
import com.ecommerce.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @GetMapping
    public Iterable<OrderDto> getAllOrders() {

        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();

    }

}
