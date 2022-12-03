package ru.kolobkevic.market.converters;

import org.springframework.stereotype.Component;
import ru.kolobkevic.market.dtos.OrderItemDto;
import ru.kolobkevic.market.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}