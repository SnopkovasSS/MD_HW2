package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;

//Базовый абстрактный класс Product. Implements Searchable.
//Содержит общую логику (name, price) для всех продуктов.

public abstract class Product implements Searchable {
    private String name;
    private double price;  // Базовая цена (fixed или base для скидки)

    //Конструктор: проверяет валидность name и price.
     //@param name Название продукта (не null/blank).
    //@param price Базовая цена (>0).
    //@throws IllegalArgumentException Если name null/blank или price <=0.

    public Product(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть null или пустым");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.name = name.trim();
        this.price = price;
    }

    //Геттер для названия.

    @Override
    public String getName() {
        return name;
    }

    //Геттер для базовой цены (переопределяется в DiscountedProduct).

    public double getPrice() {
        return price;
    }

    //Базовая реализация search term: имя + цена.
     //Наследники могут переопределить (напр. для discounted price).

    @Override
    public String getSearchTerm() {
        return name + " " + price;
    }

    //Тип контента: всегда "PRODUCT" как String.

    @Override
    public String getContentType() {
        return "PRODUCT";  // String вместо enum!
    }

    //Абстрактный метод: является ли продукт "специальным" (напр. с скидкой).
    //Реализуют наследники (Simple/Fix: false, Discounted: true если discount >0).

    public abstract boolean isSpecial();

    //Абстрактный метод для строкового представления (реализуют наследники).

    @Override
    public abstract String getStringRepresentation();

    //Абстрактный toString (реализуют наследники для форматирования).

    @Override
    public abstract String toString();
}