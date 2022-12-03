package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolobkevic.market.dtos.Cart;
import ru.kolobkevic.market.dtos.OrderDetailsDto;
import ru.kolobkevic.market.entities.Order;
import ru.kolobkevic.market.entities.OrderItem;
import ru.kolobkevic.market.entities.User;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto){
        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
        Cart currentCart = cartService.getCurrentCart(cartKey);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setPhoneNumber(orderDetailsDto.getPhone());
        List<OrderItem> items = currentCart.getOrderItemList().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setPricePerProduct(o.getPrice());
                    item.setPrice(o.getPrice());
                    item.setQuantity(o.getQuantity());
                    item.setProduct(productService.findById(o.getProductId()).orElseThrow(
                            ()->new ResourceNotFoundException("Продукт не найден")));
                    return item;
                }).collect(Collectors.toList());

        order.setItems(items);
        orderRepository.save(order);
        cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}
