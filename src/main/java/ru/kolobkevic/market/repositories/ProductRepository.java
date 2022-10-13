package ru.kolobkevic.market.repositories;

import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.model.dto.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
//    private List<Product> productList;
//
//    @PostConstruct
//    public void init() {
//        productList = new ArrayList<>(Arrays.asList(
//                new Product(1L, 98.5, "Молоко"),
//                new Product(2L, 145.8, "Чай"),
//                new Product(3L, 189.1, "Печенье"),
//                new Product(4L, 124.46, "Сок"),
//                new Product(5L, 30.0, "Хлеб")
//        ));
//    }
//
//    public List<Product> findAll() {
//        return Collections.unmodifiableList(productList);
//    }
//
//    public Product findById(Long id) {
//        return productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
//    }
//
//    public void add(Product product){
//        productList.add(product);
//    }
//
//    public void delete(Product product){
//        productList.removeIf(p -> product.getId().equals(p.getId()));
//    }
}