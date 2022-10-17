package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.model.Product;
import ru.kolobkevic.market.repositories.ProductRepository;

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

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
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