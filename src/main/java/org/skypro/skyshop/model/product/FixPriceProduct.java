package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final double FIX_PRICE = 6;
    private final UUID id;

    public FixPriceProduct(String productName) {
        super(productName);
        this.id = UUID.randomUUID();
    }

    @Override
    public double getProductPrice() {
        return FIX_PRICE;
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
        return String.format("%s : фиксированная цена %.2f", productName, FIX_PRICE);
    }
}
