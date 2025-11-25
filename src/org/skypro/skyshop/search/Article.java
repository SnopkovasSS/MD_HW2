package org.skypro.skyshop.search;

import java.util.Objects;

//Класс Article implements Searchable (для статей).
//equals/hashCode по title (name) — дубликаты по имени не добавляются в Set.

public class Article implements Searchable {
    private String title;  // = name для equals
    private String text;

    //Конструктор: проверяет title и text.
    //@param title Заголовок (name, не null/blank).
    //@param text Содержимое (не null/blank).
    //@throws IllegalArgumentException Если title/text null/blank.

    public Article(String title, String text) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Заголовок не может быть null или пустым");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть null или пустым");
        }
        this.title = title.trim();
        this.text = text.trim();
    }

    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return title;  // name = title для полиморфизма
    }

    @Override
    public String getSearchTerm() {
        return title + " " + text;  // Поиск по title + text
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        String preview = text.length() > 50 ? text.substring(0, 50) + "..." : text;
        return title + " — " + getContentType() + " [" + preview + "]";
    }

    @Override
    public String toString() {
        return "Article: " + title + "\nText: " + text;
    }

    //equals: две статьи равны, если title (name) совпадает.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);  // Только по title!
    }

    //hashCode: только по title.

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}