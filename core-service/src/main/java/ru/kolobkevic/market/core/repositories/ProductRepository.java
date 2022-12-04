package ru.kolobkevic.market.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.core.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceLessThanEqual(Pageable pageable, Double maxPrice);

    Page<Product> findAllByPriceGreaterThanEqual(Pageable pageable, Double minPrice);

    Page<Product> findAllByPriceIsBetween(Pageable pageable, Double minPrice, Double maxPrice);
}