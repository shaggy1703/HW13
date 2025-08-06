package org.skypro.skyshop.SearchEngine;

import org.skypro.skyshop.product.Searchable;
import java.util.*;

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
        Set<Searchable> result = new TreeSet<>(comparator);

        for (Searchable item : items) {
            if (item != null && item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
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
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items) {
            if (item == null) continue;
            int occurrences = countOccurrences(item.getSearchTerm(), query);
            if (occurrences > maxCount) {
                maxCount = occurrences;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено подходящих результатов для запроса: " + query);
        }

        return bestMatch;
    }
}