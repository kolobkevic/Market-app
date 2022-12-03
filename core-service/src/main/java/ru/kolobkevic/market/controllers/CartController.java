package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.dtos.Cart;
import ru.kolobkevic.market.dtos.StringResponse;
import ru.kolobkevic.market.services.CartService;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("http://localhost:3188")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        String username = checkUsername(principal);
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = checkUsername(principal);
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/delete/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = checkUsername(principal);
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        String username = checkUsername(principal);
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/decrease/{productId}")
    public void decrease(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        String username = checkUsername(principal);
        cartService.decreaseItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid){
        String username = checkUsername(principal);
        cartService.mergeCarts(getCurrentCartUuid(username, null), getCurrentCartUuid(null, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart(){
        return new StringResponse(cartService.generateCartUuid());
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }

    private String checkUsername(Principal principal) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        return username;
    }
}