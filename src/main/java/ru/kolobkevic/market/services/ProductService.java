package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.model.Product;
import ru.kolobkevic.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByPriceGreaterThanEqual(Double minPrice){
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findAllByPriceLessThanEqual(Double maxPrice){
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findAllByPriceIsBetween(Double minPrice, Double maxPrice){
        return productRepository.findAllByPriceIsBetween(minPrice, maxPrice);
    }

    public List<Product> filterByPrice(Double minPrice, Double maxPrice){
        if (minPrice == null){
            return productRepository.findAllByPriceLessThanEqual(maxPrice);
        } else if (maxPrice == null) {
            return productRepository.findAllByPriceGreaterThanEqual(minPrice);
        } else {
            return productRepository.findAllByPriceIsBetween(minPrice, maxPrice);
        }
    }
}