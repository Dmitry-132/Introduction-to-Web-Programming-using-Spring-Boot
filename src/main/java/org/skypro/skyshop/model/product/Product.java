package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final String productName;
    private final UUID id;

    public Product(String productName) throws NullPointerException {
        if (productName == null || productName.isBlank()) {
            throw new NullPointerException("имя продукта не задано");
        }
        this.productName = productName;
        this.id = UUID.randomUUID();
    }

    public String getProductName() {
        return productName;
    }

    public UUID getId() {
        return id;
    }

    public abstract boolean isSpecial();

    public abstract double getProductPrice();

    @Override
    @JsonIgnore
    public String searchTerm() {
        return productName;
    }


    @Override
    @JsonIgnore
    public String typeContent() {
        return "< PRODUCT >";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(searchTerm(), ((Product) o).searchTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchTerm());
    }

}