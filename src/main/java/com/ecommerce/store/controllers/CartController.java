package com.ecommerce.store.controllers;


import com.ecommerce.store.dtos.CartDto;
import com.ecommerce.store.mappers.CartMapper;
import com.ecommerce.store.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {


    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        var cart =cartRepository.findById(cartId).orElse(null);

        if (cart == null){
            return ResponseEntity.notFound().build();
        }



        return ResponseEntity.ok(cartMapper.toDto(cart));

    }
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @GetMapping
    public Iterable<CartDto> getAllUsers() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDto).toList();
    }


}


