package ru.kolobkevic.market.core.converters;

import org.springframework.stereotype.Component;
import ru.kolobkevic.market.core.dtos.ProductDto;
import ru.kolobkevic.market.core.entities.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getPrice(), productDto.getTitle());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getPrice(), product.getTitle());
    }
}