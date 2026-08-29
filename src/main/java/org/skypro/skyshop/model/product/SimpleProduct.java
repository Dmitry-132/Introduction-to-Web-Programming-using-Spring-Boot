package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.exception.NoSuchProductException;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final double productPrice;
    private final UUID id;

    public SimpleProduct(String productName, double productPrice) throws IllegalArgumentException {
        super(productName);
        if (productPrice < 1) {
            throw new NoSuchProductException("Цена продукта должна быть выше или ровна 1");
        }
        this.productPrice = productPrice;
        this.id = UUID.randomUUID();
    }

    @Override
    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s : %.2f ", productName, productPrice);
    }
}