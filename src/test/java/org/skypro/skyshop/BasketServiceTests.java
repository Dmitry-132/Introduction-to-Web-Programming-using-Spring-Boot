package org.skypro.skyshop;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTests {

    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    @DisplayName("Добавление несуществующего товара в корзину")
    void addProductToBasket_WhenProductNotFound() {
        UUID nonExistentId = UUID.randomUUID(); //Optional<T> — контейнер, который может содержать значение типа T или быть пустым (учитывает null).
        Mockito.when(storageService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(nonExistentId));
        verify(productBasket, never()).addInBasket(any(UUID.class)); // any - любой объект выбранного класса
    }

    @Test
    @DisplayName("Добавление существующего товара")
    void addProductToBasket_WhenProductFound() {
        Product product = new SimpleProduct("apple", 100);
        UUID productId = product.getId();
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(productId);
        verify(productBasket, times(1)).addInBasket(productId);
    }

    @Test
    @DisplayName("getUserBacket возвращает пустую корзину, если roductBasket пуст")
    void getUserBasket_WhenBasketIsEmpty() {
        Mockito.when(productBasket.getProductBasket()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();
        assertNotNull(userBasket);
        assertTrue(userBasket.getBasketItem().isEmpty());
    }

    @Test
    @DisplayName("getUserBasket возвращает подходящую корзину")
    void getUserBasket_WhenBasketHasProducts() {
        Product apple = new SimpleProduct("apple", 100);
        Product orange = new SimpleProduct("orange", 10);
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(apple.getId(), 5);
        basketMap.put(orange.getId(), 3);
        Mockito.when(productBasket.getProductBasket()).thenReturn(basketMap);
        Mockito.when(storageService.getProductById(apple.getId())).thenReturn(Optional.of(apple));
        Mockito.when(storageService.getProductById(orange.getId())).thenReturn(Optional.of(orange));

        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(2, userBasket.getBasketItem().size());
        assertEquals(530.0, userBasket.getTotal());
    }
}
