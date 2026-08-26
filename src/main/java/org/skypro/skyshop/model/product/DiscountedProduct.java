package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.exception.NoSuchProductException;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int discountInWholePercentages;
    private double basePrice;
    private final UUID id;

    public DiscountedProduct(String productName, double basePrice, int discountInWholePercentages) throws IllegalArgumentException {
        super(productName);
        if (basePrice < 1) {
            throw new NoSuchProductException("Цена продукта должна быть выше или ровна 1");
        }
        if (discountInWholePercentages < 0 || discountInWholePercentages > 100) {
            throw new NoSuchProductException("процент скидки некорректен");
        }
        this.basePrice = basePrice;
        this.discountInWholePercentages = discountInWholePercentages;
        this.id = UUID.randomUUID();
    }

    @Override
    public double getProductPrice() {
        return basePrice - ((basePrice * discountInWholePercentages) / 100);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s : %.2f (%d %%)", productName, getProductPrice(), discountInWholePercentages);
    }
}