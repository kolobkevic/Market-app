package ru.kolobkevic.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolobkevic.market.entities.Product;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private double price;
    private double totalPrice;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.price = product.getPrice();
        this.totalPrice = product.getPrice();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.totalPrice = this.quantity * this.price;
    }
}
