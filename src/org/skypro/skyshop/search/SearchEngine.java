package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchEngine {
    private List<Searchable> items;  // Новая структура: динамический список (вместо массива)
    private int maxSize;  // Лимит для add() (из конструктора; если =0 — безлимит)

    //Конструктор: инициализирует список и лимит.
    //@param maxSize максимальный размер (0 = безлимит)

    public SearchEngine(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
    }

    //Добавляет элемент в список (если < maxSize и не null).

    public void add(Searchable item) {
        if (item != null && (maxSize == 0 || items.size() < maxSize)) {
            items.add(item);
        }
    }

    //Возвращает текущий размер списка.
    public int getCurrentSize() {
        return items.size();
    }

    //Ищет ВСЕ элементы, где getSearchTerm() содержит query (case-insensitive).
    //@return List всех подходящих (пустой, если ничего нет или query blank/null)

    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return results;  // Пустой для blank
        }
        String lowerQuery = query.toLowerCase();
        for (Searchable s : items) {
            if (s != null && s.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(s);  // Добавляем ВСЕ релевантные (без лимита 5)
            }
        }
        return results;
    }

    //Находит элемент с максимальным кол-вом вхождений query в getSearchTerm().
    //Если max=0 или query blank — throw BestResultNotFound.
     //* @return Searchable с max вхождениями (любой, если несколько с max)
     //* @throws BestResultNotFound если ничего не найдено

    public Searchable findMostRelevant(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound("Не найден ни один результат для запроса '" + query + "'");
        }
        String lowerQuery = query.toLowerCase();
        Searchable best = null;
        int maxOccurrences = -1;
        for (Searchable s : items) {  // Итерация по List (вместо массива)
            if (s != null) {
                int occurrences = countOccurrences(s.getSearchTerm().toLowerCase(), lowerQuery);
                if (occurrences > maxOccurrences) {
                    maxOccurrences = occurrences;
                    best = s;
                }
            }
        }
        if (best == null || maxOccurrences == 0) {
            throw new BestResultNotFound("Не найден ни один результат для запроса '" + query + "'");
        }
        return best;
    }

    // Вспомогательный метод: подсчёт вхождений (non-overlapping, как раньше)
    private int countOccurrences(String text, String sub) {
        if (sub == null || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }
}