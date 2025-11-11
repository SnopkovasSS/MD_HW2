package org.skypro.skyshop.product;

public class Product {
    private final String name;  // Немодифицируемое поле (final)
    private final int price;    // Немодифицируемое поле (final)

    // Единственный конструктор
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Геттеры для доступа
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
