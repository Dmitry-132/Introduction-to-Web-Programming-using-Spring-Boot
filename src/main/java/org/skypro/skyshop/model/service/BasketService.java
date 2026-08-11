package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void addProductToBasket(UUID productId) {
        storageService.getProductById(productId).orElseThrow(() -> new IllegalArgumentException("Товар с id " + productId + " не найден"));
        productBasket.addInBasket(productId);
    }

    public UserBasket getUserBasket() { // вроде работает
        return new UserBasket(productBasket.getProductBasket().entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey())
                        .orElseThrow(() -> new IllegalArgumentException("Товар с id " + entry.getKey() + " не найден")),
                        entry.getValue())).toList());
    }
}
