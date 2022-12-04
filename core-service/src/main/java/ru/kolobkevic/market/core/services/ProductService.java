package ru.kolobkevic.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.api.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.core.dtos.ProductDto;
import ru.kolobkevic.market.core.repositories.ProductRepository;
import ru.kolobkevic.market.core.entities.Product;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Page<Product> findAllInOnePage() {
        Pageable wholePage = Pageable.unpaged();
        return productRepository.findAll(wholePage);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product update(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(
                ()->new ResourceNotFoundException("Продукт не найден в базе, id: " + productDto.getId()));
        product.setPrice(product.getPrice());
        product.setTitle(product.getTitle());
        return product;
    }

    public Page<Product> findAllByPriceGreaterThanEqual(int pageIndex, int pageSize, Double minPrice){
        return productRepository.findAllByPriceGreaterThanEqual(PageRequest.of(pageIndex, pageSize), minPrice);
    }

    public Page<Product> findAllByPriceLessThanEqual(int pageIndex, int pageSize, Double maxPrice){
        return productRepository.findAllByPriceLessThanEqual(PageRequest.of(pageIndex, pageSize), maxPrice);
    }

    public Page<Product> findAllByPriceIsBetween(int pageIndex, int pageSize, Double minPrice, Double maxPrice){
        return productRepository.findAllByPriceIsBetween(PageRequest.of(pageIndex, pageSize), minPrice, maxPrice);
    }
}