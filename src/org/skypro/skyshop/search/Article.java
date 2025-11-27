package org.skypro.skyshop.search;

//Статья (implements Searchable).

public class Article implements Searchable {
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getStringRepresentation() {
        return "Article: title='" + title + "', content='" + content + "'";
    }

    @Override
    public String getContentType() {
        return "article";  // String
    }
}