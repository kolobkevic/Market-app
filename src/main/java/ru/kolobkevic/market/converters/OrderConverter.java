package ru.kolobkevic.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kolobkevic.market.dtos.OrderDto;
import ru.kolobkevic.market.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public Order dtoToEntity(OrderDto orderDto) {
        throw new UnsupportedOperationException();
    }

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(order.getAddress());
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUser().getUsername());
        orderDto.setPhoneNumber(order.getPhoneNumber());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}