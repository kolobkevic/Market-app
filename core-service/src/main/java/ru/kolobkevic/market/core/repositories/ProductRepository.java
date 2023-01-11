package ru.kolobkevic.market.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.core.entities.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceLessThanEqual(Pageable pageable, BigDecimal maxPrice);

    Page<Product> findAllByPriceGreaterThanEqual(Pageable pageable, BigDecimal minPrice);

    Page<Product> findAllByPriceIsBetween(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);
}