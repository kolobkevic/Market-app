package ru.kolobkevic.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolobkevic.market.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private Double price;
    private String title;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.title = product.getTitle();
    }
}