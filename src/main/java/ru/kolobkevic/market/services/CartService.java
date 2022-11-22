package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.dtos.Cart;
import ru.kolobkevic.market.entities.Product;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductById(Long productId) {
        if (!getCurrentCart().addProductById(productId)) {
            Product product = productService.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("Продукт не найден"));
            getCurrentCart().addProduct(product);
        }
    }

    public void removeProductById(Long productId){
        getCurrentCart().removeProduct(productId);
    }

    public void clear(){
        getCurrentCart().clear();
    }
}
