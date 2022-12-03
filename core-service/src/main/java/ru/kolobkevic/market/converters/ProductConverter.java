package ru.kolobkevic.market.converters;

import org.springframework.stereotype.Component;
import ru.kolobkevic.market.dtos.ProductDto;
import ru.kolobkevic.market.entities.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getPrice(), productDto.getTitle());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getPrice(), product.getTitle());
    }
}