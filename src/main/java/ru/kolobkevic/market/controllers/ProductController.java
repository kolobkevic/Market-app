package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.model.dto.Product;
import ru.kolobkevic.market.services.ProductService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> showAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping
    public void saveOrUpdate(@RequestBody Product product){
        productService.saveOrUpdateProduct(product);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        productService.saveOrUpdateProduct(product);
        return product;
    }
}
