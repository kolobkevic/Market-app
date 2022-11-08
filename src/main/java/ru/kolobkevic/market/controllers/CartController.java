package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.dtos.ProductDto;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.services.ProductService;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final ProductService productService;
    private final List<ProductDto> productList;

    @GetMapping
    public List<ProductDto> findAll() {
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Long id) {
        productList.add(new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found"))));
    }

    @GetMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productList.removeIf(productDto -> productDto.getId().equals(id));
    }
}
