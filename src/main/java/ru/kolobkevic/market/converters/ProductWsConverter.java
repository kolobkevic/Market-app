package ru.kolobkevic.market.converters;

import org.springframework.stereotype.Component;
import ru.kolobkevic.market.entities.Product;
import ru.kolobkevic.market.ws.products.ProductDtoWs;

@Component
public class ProductWsConverter {
    public Product dtoToEntity(ProductDtoWs productDtoWs) {
        return new Product(productDtoWs.getId(), productDtoWs.getPrice(), productDtoWs.getTitle());
    }

    public ProductDtoWs entityToDto(Product product) {
        ProductDtoWs productDtoWs = new ProductDtoWs();
        productDtoWs.setId(product.getId());
        productDtoWs.setPrice(product.getPrice());
        productDtoWs.setTitle(product.getTitle());
        return productDtoWs;
    }
}