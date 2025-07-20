package org.skypro.skyshop.SearchEngine;

import org.skypro.skyshop.product.Searchable;


public class SearchEngine {
    private final Searchable[] items;
    private int count;


    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        if (count < items.length) {
            items[count++] = item;
        } else {
            System.out.println("Невозможно добавить элемент: достигнут лимит.");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int index = 0;

        for (Searchable item : items) {
            if (item == null) continue;
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result[index++] = item;
                if (index == result.length) break;
            }
        }

        return result;
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
