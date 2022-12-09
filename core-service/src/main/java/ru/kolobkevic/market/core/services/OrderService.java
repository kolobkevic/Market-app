package ru.kolobkevic.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolobkevic.market.api.dtos.CartDto;
import ru.kolobkevic.market.api.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.api.dtos.OrderDetailsDto;
import ru.kolobkevic.market.core.entities.Order;
import ru.kolobkevic.market.core.entities.OrderItem;
import ru.kolobkevic.market.core.integrations.CartServiceIntegration;
import ru.kolobkevic.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto){
        CartDto currentCart = cartServiceIntegration.getUserCart(username);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setPhoneNumber(orderDetailsDto.getPhone());
        List<OrderItem> items = currentCart.getCartItemList().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setPricePerProduct(o.getPrice());
                    item.setPrice(o.getTotalPrice());
                    item.setQuantity(o.getQuantity());
                    item.setProduct(productService.findById(o.getProductId()).orElseThrow(
                            ()->new ResourceNotFoundException("Продукт не найден")));
                    return item;
                }).collect(Collectors.toList());

        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}
