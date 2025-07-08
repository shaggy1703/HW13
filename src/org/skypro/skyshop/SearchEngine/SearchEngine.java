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
}