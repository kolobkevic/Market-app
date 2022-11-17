package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.dtos.ProductDto;
import ru.kolobkevic.market.exceptions.DataValidationException;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.entities.Product;
import ru.kolobkevic.market.services.ProductService;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex,
                                    @RequestParam(required = false, name = "minPrice") Double minPrice,
                                    @RequestParam(required = false, name = "maxPrice") Double maxPrice) {
        int pageSize = 10;
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        if ((minPrice != null) && (maxPrice != null)) {
            return productService.findAllByPriceIsBetween(pageIndex - 1, pageSize, minPrice, maxPrice)
                    .map(ProductDto::new);
        }
        if ((minPrice == null) && (maxPrice != null)) {
            return productService.findAllByPriceLessThanEqual(pageIndex - 1, pageSize, maxPrice)
                    .map(ProductDto::new);
        }
        if (minPrice != null) {
            return productService.findAllByPriceGreaterThanEqual(pageIndex - 1, pageSize, minPrice)
                    .map(ProductDto::new);
        }
        return productService.findAll(pageIndex - 1, pageSize).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found")));
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
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        productService.save(product);
        return new ProductDto(product);
    }
}
