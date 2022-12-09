package ru.kolobkevic.market.cart.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kolobkevic.market.api.dtos.CartDto;
import ru.kolobkevic.market.api.dtos.CartItemDto;
import ru.kolobkevic.market.cart.model.Cart;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CartConverter {
    public CartDto modelToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getCartItemList().stream().map(items ->
                new CartItemDto(items.getProductId(), items.getProductTitle(), items.getQuantity(),
                        items.getPrice(), items.getTotalPrice())
        ).collect(Collectors.toList());
        return new CartDto(cartItemDtos, cart.getTotalPrice());
    }
}