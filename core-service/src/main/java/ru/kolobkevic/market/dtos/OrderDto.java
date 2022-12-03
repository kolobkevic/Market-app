package ru.kolobkevic.market.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private Double totalPrice;
    private String address;
    private String phoneNumber;
}
