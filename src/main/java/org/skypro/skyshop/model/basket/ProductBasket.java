package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID,Integer> productBasket = new HashMap<>();

    public void addInBasket(UUID productId) {
        productBasket.merge(productId, 1, Integer::sum);
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(productBasket);  //обёртка защищает от изменений
    }
}
