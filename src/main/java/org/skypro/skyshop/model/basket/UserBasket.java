package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
private final List<BasketItem> basketItem;
private final double total;

    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = List.copyOf(basketItem);
        this.total = basketItem.stream().mapToDouble(b -> b.getProduct().getProductPrice()*b.getQuantity()).sum();
    }

    public List<BasketItem> getBasketItem() {
        return basketItem;
    }

    public double getTotal() {
        return total;
    }
}
