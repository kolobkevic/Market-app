package ru.kolobkevic.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceLessThanEqual(Double maxPrice);

    List<Product> findAllByPriceGreaterThanEqual(Double minPrice);

    List<Product> findAllByPriceIsBetween(Double minPrice, Double maxPrice);
}