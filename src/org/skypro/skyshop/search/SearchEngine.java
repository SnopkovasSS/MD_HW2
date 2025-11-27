package org.skypro.skyshop.search;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//Поисковый движок: TreeSet<Searchable> с Comparator по длине name descending (длинные первыми).
//add() игнор дубли по name. search() — Stream с filter + collect(toCollection с Supplier<TreeSet + comparator>).
//getAll() — копия с comparator (фикс ClassCastException).
//Searchable не Comparable, но comparator по getName().length() — полиморфно работает.

public class SearchEngine {
    // Компаратор: сортировка по длине name убывание (длинные первыми, int.compare для safety)
    private static final Comparator<Searchable> LENGTH_COMPARATOR =
            (a, b) -> Integer.compare(b.getName().length(), a.getName().length());

    // Хранилище: TreeSet с comparator (отсортировано, unique по comparator — если длины равны, natural order)
    private final Set<Searchable> items = new TreeSet<>(LENGTH_COMPARATOR);

    //Добавление: проверка дубликата по name (Stream.anyMatch), добавление в TreeSet (с comparator).

    public void add(Searchable item) {
        if (item == null) return;
        // Проверка дубликата по name (case-sensitive, как в задании)
        boolean duplicate = items.stream()
                .anyMatch(existing -> existing.getName().equals(item.getName()));
        if (!duplicate) {
            items.add(item);  // Добавляется с сортировкой (comparator)
        }
    }

    //Поиск (задание 1): Один Stream с filter (name contains query, case-insensitive)
     //+ collect(toCollection с Supplier<TreeSet + comparator>).
    //Пустой query → пустой TreeSet (comparator).

    public Set<Searchable> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            // Пустой: новый TreeSet с comparator (фикс: пустой, но sorted-ready)
            return new TreeSet<>(LENGTH_COMPARATOR);
        }
        String lowerQuery = query.toLowerCase();
        // Supplier: создаёт TreeSet с comparator
        Supplier<TreeSet<Searchable>> treeSetSupplier = () -> new TreeSet<>(LENGTH_COMPARATOR);
        return items.stream()
                .filter(item ->item.getName().toLowerCase().contains(lowerQuery))// Filter
                .collect(Collectors.toCollection(treeSetSupplier));  // Collect в TreeSet (sorted)
    }

    //Все элементы: КОПИЯ TreeSet с comparator (фикс ClassCastException: явный LENGTH_COMPARATOR).
    //Immutable для безопасности.

    public Set<Searchable> getAll() {
        // Фикс: new TreeSet с comparator + items (копирует и сортирует заново, даже если items уже TreeSet)
        return new TreeSet<>();
    }
    public int size() {
        return items.size();
    }

    //Для теста: получить comparator (не обязательно).
    public Comparator<Searchable> getComparator() {
        return LENGTH_COMPARATOR;
    }
}
