package org.skypro.skyshop.SearchEngine;

import org.skypro.skyshop.product.Searchable;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items;
    private final SearchableComparator comparator;

    public SearchEngine(int capacity) {
        this.items = new HashSet<>();
        this.comparator = new SearchableComparator();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        return items.stream()
                .filter(item -> item != null &&
                        item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public int getSize() {
        return items.size();
    }

    private int countOccurrences(String text, String substring) {
        if (substring == null || substring.isEmpty() || text == null) {
            return 0;
        }
        int count = 0;
        int index = 0;
        substring = substring.toLowerCase();
        text = text.toLowerCase();

        while (true) {
            index = text.indexOf(substring, index);
            if (index == -1) break;
            count++;
            index += substring.length();
        }

        return count;
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Поисковая строка не может быть пустой.");
        }

        return items.stream()
                .filter(Objects::nonNull)
                .filter(item -> countOccurrences(item.getSearchTerm(), query) > 0)
                .max(Comparator.comparingInt(item -> countOccurrences(item.getSearchTerm(), query)))
                .orElseThrow(() -> new BestResultNotFound("Не найдено подходящих результатов для запроса: " + query));
    }
}