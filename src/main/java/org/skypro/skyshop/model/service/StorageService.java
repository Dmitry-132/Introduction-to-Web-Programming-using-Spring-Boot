package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        fillData();
    }

    private void fillData() {
        Product orange = new SimpleProduct("orange", 20);
        Product apple = new FixPriceProduct("apple");
        Product banana = new SimpleProduct("banana", 20);
        Product egg = new DiscountedProduct("egg", 15, 25);
        Product carrot = new FixPriceProduct("carrot");
        Product potato = new DiscountedProduct("potato", 10, 50);

        Article aboutPlantingOranges = new Article("about planting oranges",
                "Oranges can be grown in various ways: from seeds at home or in the open field");
        Article eggsExpensive = new Article("Eggs are expensive",
                "Retail egg prices continued to fall in February, according to the latest consumer price index." +
                        " Prices are now down 42.1% from a year ago.");

        add(orange);
        add(apple);
        add(banana);
        add(egg);
        add(carrot);
        add(potato);

        add(aboutPlantingOranges);
        add(eggsExpensive);
    }

    public Collection<Searchable> allSearchable() {
        Collection<Searchable> all = new ArrayList<>();
        all.addAll(productMap.values());
        all.addAll(articleMap.values());
        return all;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public void add (Product product) {
        productMap.put(product.getId(), product);
    }

    public void add (Article article) {
        articleMap.put(article.getId(),article);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }


}
