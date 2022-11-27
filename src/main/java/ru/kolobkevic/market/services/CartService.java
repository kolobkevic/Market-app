package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.dtos.Cart;
import ru.kolobkevic.market.entities.Product;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    public String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void addToCart(String cartKey, Long productId) {
        Product product = productService.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Продукт не найден"));
        execute(cartKey, c -> c.add(productId));
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.remove(productId));
    }

    public void decreaseItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.decrease(productId));
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void mergeCarts(String userCartKey, String guestCartKey){
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }
}