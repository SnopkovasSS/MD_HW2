package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;  // Импорт обязательный!

public class Article implements Searchable {
    private final String title;  // Немодифицируемое
    private final String text;   // Немодифицируемое

    // Конструктор
    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Геттеры (без сеттеров)
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    // toString(): "Название\nТекст" (как в шаге 1)
    @Override
    public String toString() {
        return title + "\n" + text;
    }

    // Реализация Searchable (обязательно!)
    @Override
    public String getSearchTerm() {
        return toString();  // Поиск по полному тексту: название + текст
    }

    @Override
    public String getContentType() {
        return "ARTICLE";  // Тип: ARTICLE
    }

    @Override
    public String getName() {
        return getTitle();  // Имя = название статьи
    }

    // getStringRepresentation() — используем default из интерфейса (не переопределяем)
}