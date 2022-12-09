package ru.kolobkevic.market.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private Double price;
    private Double totalPrice;

    public CartItemDto() {
    }
}