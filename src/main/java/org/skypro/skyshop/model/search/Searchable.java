package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();
    String typeContent();

    default String getStringRepresentation() {
        return searchTerm() + " " + typeContent();
    }

    default boolean searchForMatches(String seek) {
        return searchTerm().toLowerCase().contains(seek.toLowerCase());
    }

    public UUID getId();
}