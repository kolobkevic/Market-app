package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.dtos.Cart;
import ru.kolobkevic.market.services.CartService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;


    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addById(@PathVariable Long id) {
        cartService.addProductById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.removeProductById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }
}
