package ru.kolobkevic.market.api.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItemList;
    private Double totalPrice;

    public CartDto(List<CartItemDto> items, Double totalPrice) {
        this.cartItemList = items;
        this.totalPrice = totalPrice;
    }
    public CartDto() {
    }

}