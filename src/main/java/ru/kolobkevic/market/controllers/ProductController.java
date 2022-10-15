package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.dtos.ProductDto;
import ru.kolobkevic.market.model.Product;
import ru.kolobkevic.market.services.ProductService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll(@RequestParam(required = false, name = "minPrice") Double minPrice, @RequestParam(required = false, name = "maxPrice") Double maxPrice){
        if ((minPrice != null) && (maxPrice != null)) {
            return productService.findAllByPriceIsBetween(minPrice, maxPrice);
        }
        if ((minPrice == null) && (maxPrice != null)){
            return productService.findAllByPriceLessThanEqual(maxPrice);
        }
        if (minPrice != null){
            return productService.findAllByPriceGreaterThanEqual(minPrice);
        }
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public void saveOrUpdate(@RequestBody Product product) {
        productService.save(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        productService.save(product);
        return new ProductDto(product);
    }
}
