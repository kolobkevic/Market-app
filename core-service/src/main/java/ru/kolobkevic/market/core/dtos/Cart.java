package ru.kolobkevic.market.core.dtos;

import lombok.Data;
import ru.kolobkevic.market.core.entities.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> orderItemList;
    private Double totalPrice;

    public Cart() {
        this.orderItemList = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (OrderItemDto orderItem : orderItemList) {
            if (orderItem.getProductId().equals(productId)) {
                orderItem.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        if (add(product.getId())) {
            return;
        }
        orderItemList.add(new OrderItemDto(product));
        recalculate();
    }

    public void decrease(Long productId) {
        Iterator<OrderItemDto> iter = orderItemList.iterator();
        while (iter.hasNext()) {
            OrderItemDto orderItem = iter.next();
            if (orderItem.getProductId().equals(productId)) {
                orderItem.changeQuantity(-1);
                if (orderItem.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        orderItemList.removeIf(o -> o.getProductId().equals(productId));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0d;
        totalPrice = orderItemList.stream().mapToDouble(OrderItemDto::getPrice).sum();
    }

    public void clear() {
        orderItemList.clear();
        totalPrice = 0d;
    }

    public void merge(Cart secondCart){
        for(OrderItemDto secondItem: secondCart.getOrderItemList()){
            boolean isMerged = false;
            for(OrderItemDto firstItem: orderItemList){
                if(firstItem.getProductId().equals(secondItem.getProductId())){
                    firstItem.changeQuantity(secondItem.getQuantity());
                    isMerged = true;
                    break;
                }
            }
            if(!isMerged){
                orderItemList.add(secondItem);
            }
        }
        recalculate();
        secondCart.clear();
    }
}