package org.skypro.skyshop.product;

//Продукт с фиксированной ценой (без скидок). Наследует Product, implements Searchable.

public class FixPriceProduct extends Product {

    //Конструктор: устанавливает name и fixed price.
     // @param name Название продукта (не null/blank).
    //@param price Фиксированная цена (>0).
    //@throws IllegalArgumentException Если name null/blank или price <=0.

    public FixPriceProduct(String name, double price) {
        super(name, price);  // Вызываем конструктор Product (проверяет валидность)
    }

    //Переопределение: не специальный продукт (нет скидки).

    @Override
    public boolean isSpecial() {
        return false;
    }

    //Переопределение: строковое представление с фиксированной ценой.

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (фиксированная цена)";
    }

    //Переопределение: search term (имя + цена).

    @Override
    public String getSearchTerm() {
        return getName() + " " + getPrice();
    }

    //Переопределение: тип контента как String.

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    //Переопределение: строковое представление для вывода.

    @Override
    public String getStringRepresentation() {
        return getName() + " — " + getContentType() + " [fixed price: " + getPrice() + "]";
    }

    //Переопределение: имя от super.

    @Override
    public String getName() {
        return super.getName();
    }
}