package org.skypro.skyshop.search;

public interface Searchable {
    // Текст для поиска
    String getSearchTerm();

    // Тип контента ("PRODUCT" или "ARTICLE")
    String getContentType();

    // Имя объекта
    String getName();

    // Default: "имя — тип" (для вывода)
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}
