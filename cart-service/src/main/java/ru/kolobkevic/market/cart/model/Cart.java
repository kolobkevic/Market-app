package ru.kolobkevic.market.cart.model;

import lombok.Data;
import ru.kolobkevic.market.api.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItemList;
    private BigDecimal totalPrice;

    public Cart() {
        this.cartItemList = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProductId().equals(productId)) {
                cartItem.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(ProductDto product) {
        if (add(product.getId())) {
            return;
        }
        cartItemList.add(new CartItem(product));
        recalculate();
    }

    public void decrease(Long productId) {
        Iterator<CartItem> iter = cartItemList.iterator();
        while (iter.hasNext()) {
            CartItem cartItem = iter.next();
            if (cartItem.getProductId().equals(productId)) {
                cartItem.changeQuantity(-1);
                if (cartItem.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        cartItemList.removeIf(o -> o.getProductId().equals(productId));
        recalculate();
    }

    private void recalculate() {
        totalPrice = BigDecimal.valueOf(0);
        for (CartItem o : cartItemList) {
            totalPrice = totalPrice.add(o.getTotalPrice());
        }
    }

    public void clear() {
        cartItemList.clear();
        totalPrice = BigDecimal.valueOf(0);
    }

    public void merge(Cart secondCart){
        for(CartItem secondItem: secondCart.getCartItemList()){
            boolean isMerged = false;
            for(CartItem firstItem: cartItemList){
                if(firstItem.getProductId().equals(secondItem.getProductId())){
                    firstItem.changeQuantity(secondItem.getQuantity());
                    isMerged = true;
                    break;
                }
            }
            if(!isMerged){
                cartItemList.add(secondItem);
            }
        }
        recalculate();
        secondCart.clear();
    }
}