package com.ecommerce.store.mappers;

import com.ecommerce.store.dtos.OrderDto;
import com.ecommerce.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
