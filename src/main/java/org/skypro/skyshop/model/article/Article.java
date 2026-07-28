package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String titleOfArticle;
    private final String textOfArticle;
    private final UUID id;

    public Article(String titleOfArticle, String textOfArticle) {
        this.titleOfArticle = titleOfArticle;
        this.textOfArticle = textOfArticle;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return titleOfArticle + "\n " + textOfArticle;
    }

    @Override
    @JsonIgnore
    public String searchTerm() {
        return titleOfArticle;
    }

    @Override
    @JsonIgnore
    public String typeContent() {
        return "< ARTICLE >";
    }

    public UUID getId() {
        return this.id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(searchTerm(), ((Article) o).searchTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchTerm());
    }

}
