package ru.kolobkevic.market.cart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kolobkevic.market.api.dtos.ProductDto;
import ru.kolobkevic.market.cart.integration.ProductServiceIntegration;
import ru.kolobkevic.market.cart.services.CartService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @BeforeEach
    public void initCart(){
        cartService.clearCart("test_cart");
    }

    @Test
    public void addToCartTest(){
        ProductDto productDto = new ProductDto(5L, BigDecimal.valueOf(20), "test");
        ProductDto productDto1 = new ProductDto(6L, BigDecimal.valueOf(2), "test1");

        Mockito.doReturn(Optional.of(productDto)).when(productServiceIntegration).getProductById(5L);
        Mockito.doReturn(Optional.of(productDto1)).when(productServiceIntegration).getProductById(6L);
        cartService.addToCart("test_cart", 5L);
        cartService.addToCart("test_cart", 6L);
        cartService.addToCart("test_cart", 5L);
        Assertions.assertEquals(2, cartService.getCurrentCart("test_cart").getCartItemList().size());
        Assertions.assertEquals(BigDecimal.valueOf(42), cartService.getCurrentCart("test_cart").getTotalPrice());
    }
}