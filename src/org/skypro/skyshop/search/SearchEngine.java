package org.skypro.skyshop.search;

import org.skypro.skyshop.product.Product;
import java.util.*;

//Поисковый движок для продуктов и статей.
//Внутренне HashSet для дубликатов (по equals/hashCode на name).
//search() и getAll() возвращают TreeSet с сортировкой: убывание по длине getName(), затем алфавит по name.

public class SearchEngine {
    private Set<Searchable> searchableItems;

    public SearchEngine() {
        this.searchableItems = new HashSet<Searchable>();
    }

    public boolean add(Searchable searchable) {
        if (searchable == null) {
            return false;
        }
        return searchableItems.add(searchable);
    }

    public Set<Searchable> search(String searchTerm) {
        Comparator<Searchable> comparator = new Comparator<Searchable>() {
            @Override
            public int compare(Searchable a, Searchable b) {
                int lenA = a.getName().length();
                int lenB = b.getName().length();
                if (lenA != lenB) {
                    return Integer.compare(lenB, lenA);
                }
                return a.getName().compareToIgnoreCase(b.getName());
            }
        };

        TreeSet<Searchable> results = new TreeSet<Searchable>(comparator);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }
        String term = searchTerm.toLowerCase().trim();
        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase().contains(term)) {
                results.add(item);
            }
        }
        return results;
    }

    public Set<Searchable> getAll() {
        Comparator<Searchable> comparator = new Comparator<Searchable>() {
            @Override
            public int compare(Searchable a, Searchable b) {
                int lenA = a.getName().length();
                int lenB = b.getName().length();
                if (lenA != lenB) {
                    return Integer.compare(lenB, lenA);
                }
                return a.getName().compareToIgnoreCase(b.getName());
            }
        };
        return new TreeSet<Searchable>();
    }

    public int size() {
        return searchableItems.size();
    }
}


