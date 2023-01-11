package ru.kolobkevic.market.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public CartItemDto() {
    }
}