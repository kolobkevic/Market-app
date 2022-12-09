package ru.kolobkevic.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.api.dtos.CartDto;
import ru.kolobkevic.market.api.dtos.StringResponse;
import ru.kolobkevic.market.cart.converters.CartConverter;
import ru.kolobkevic.market.cart.services.CartService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/{uuid}")
    public CartDto getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        return cartConverter.modelToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/delete/{productId}")
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/decrease/{productId}")
    public void decrease(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId){
        cartService.decreaseItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader String username, @PathVariable String uuid){
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
}