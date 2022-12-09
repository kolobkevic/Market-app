package ru.kolobkevic.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kolobkevic.market.api.dtos.OrderDto;
import ru.kolobkevic.market.core.entities.Order;

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
        orderDto.setUsername(order.getUsername());
        orderDto.setPhoneNumber(order.getPhoneNumber());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}