package org.skypro.skyshop.model.service;


import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String seek) {
        if (seek == null || seek.trim().length() < 3) {
            return java.util.Collections.emptyList();
        }
        return storageService.allSearchable().stream()
                .filter(s -> s.searchTerm().toLowerCase().contains(seek.trim().toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
