package ru.kolobkevic.market.dtos;

import lombok.Data;
import ru.kolobkevic.market.entities.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> orderItemList;
    private double totalPrice;

    public Cart() {
        this.orderItemList = new ArrayList<>();
    }

    public boolean addProductById(Long id) {
        for (OrderItemDto orderItem : orderItemList) {
            if (orderItem.getProductId().equals(id)) {
                orderItem.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decreaseProductById(Long id) {
        Iterator<OrderItemDto> iter = orderItemList.iterator();
        while (iter.hasNext()) {
            OrderItemDto orderItem = iter.next();
            if (orderItem.getProductId().equals(id)) {
                orderItem.changeQuantity(-1);
                if (orderItem.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void addProduct(Product product) {
        if (addProductById(product.getId())) {
            return;
        }
        orderItemList.add(new OrderItemDto(product));
        recalculate();
    }

    public void removeProduct(Long id) {
        orderItemList.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        totalPrice = orderItemList.stream().mapToDouble(OrderItemDto::getPrice).sum();
    }

    public void clear() {
        orderItemList.clear();
        totalPrice = 0;
    }
}