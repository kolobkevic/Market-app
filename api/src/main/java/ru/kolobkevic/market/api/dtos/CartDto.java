package ru.kolobkevic.market.api.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItemList;
    private BigDecimal totalPrice;

    public CartDto(List<CartItemDto> items, BigDecimal totalPrice) {
        this.cartItemList = items;
        this.totalPrice = totalPrice;
    }
    public CartDto() {
    }

}