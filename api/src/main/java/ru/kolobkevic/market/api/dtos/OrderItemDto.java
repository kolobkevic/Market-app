package ru.kolobkevic.market.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public OrderItemDto(ProductDto product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.price = product.getPrice();
        this.totalPrice = product.getPrice();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.totalPrice = this.price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
