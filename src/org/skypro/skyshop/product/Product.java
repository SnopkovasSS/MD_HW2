package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;
import java.util.Objects;  // Для Objects.equals/hashCode

//Базовый абстрактный класс Product. Implements Searchable.
//Содержит общую логику (name, price) для всех продуктов.
//equals/hashCode по name (для Set без дубликатов).
public abstract class Product implements Searchable {
    private String name;
    private double price;  // Базовая цена (fixed или base для скидки)

    //Конструктор: проверяет валидность name и price.
    //@param name Название продукта (не null/blank).
    //@param price Базовая цена (>0).
    //@throws IllegalArgumentException
    //Если name null/blank или price <=0.

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
        return "PRODUCT";
    }

    //Абстрактный метод: является ли продукт "специальным" (напр. с скидкой).

    public abstract boolean isSpecial();

    //Абстрактный метод для строкового представления (реализуют наследники).

    @Override
    public abstract String getStringRepresentation();

    //Абстрактный toString (реализуют наследники для форматирования).

    @Override
    public abstract String toString();

    //equals: два продукта равны, если name совпадает (игнорируя price/type).
    //Для Set: дубликаты по имени не добавляются.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  // Только с тем же subclass (Simple vs Discounted)?
        // Или используй instanceof для всех Product? Для строгого: по getClass().
        // Задание: по name, так что instanceof Product.
        Product product = (Product) o;
        return Objects.equals(name, product.name);  // Только по name!
    }

    //hashCode: только по name (для эффективности в HashSet).

    @Override
    public int hashCode() {
        return Objects.hash(name);  // Только name!
    }
}