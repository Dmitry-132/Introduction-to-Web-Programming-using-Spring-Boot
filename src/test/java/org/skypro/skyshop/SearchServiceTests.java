package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTests {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void configureMock() { //lenient() убирает жесткое использование метода, разрешая его переопределять
        Mockito.lenient().doReturn(List.of(new SimpleProduct("TestProductOne", 10),
                new SimpleProduct("TestProductTwo", 100),
                new SimpleProduct("TESTProductThree", 1000))).when(storageService).allSearchable();
    }

    @Test
    @DisplayName("если вдруг fillData() пуст")
    void search_WhenDoNotHaveAnObject() {
        Mockito.doReturn(List.of()).when(storageService).allSearchable();
        Collection<SearchResult> results = searchService.search("Test");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("лишний символ в запросе")
    void search_WhenDoNotHaveAnRightObject() {
        Collection<SearchResult> results = searchService.search("Tests");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("успешный поиск + регистр")
    void search_WhenHaveObject() {
        Collection<SearchResult> results = searchService.search("Test");
        assertEquals(List.of("TestProductOne", "TestProductTwo", "TESTProductThree"), results.stream()
                .map(SearchResult::getName).toList());
    }

    @Test
    @DisplayName("запрос в пару символов")
    void search_WhenSearchQueryIsTooShort() {
        Collection<SearchResult> results = searchService.search("Te");
        assertTrue(results.isEmpty());
    }
}
