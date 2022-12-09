package ru.kolobkevic.market.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolobkevic.market.api.dtos.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private Double price;
    private Double totalPrice;

    public CartItem(ProductDto productDto) {
        this.productId = productDto.getId();
        this.productTitle = productDto.getTitle();
        this.quantity = 1;
        this.price = productDto.getPrice();
        this.totalPrice = productDto.getPrice();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.totalPrice = this.quantity * this.price;
    }
}
