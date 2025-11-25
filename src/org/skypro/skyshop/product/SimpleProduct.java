package org.skypro.skyshop.product;

//Простой продукт без скидки. Наследует Product.
public class SimpleProduct extends Product {

    //Конструктор: устанавливает name и price.
    //@param name Название продукта (не null/blank).
    //@param price Цена (>0).

    public SimpleProduct(String name, double price) {
        super(name, price);  // Вызываем базовый конструктор (проверяет валидность)
    }

    //Переопределение: не специальный продукт (нет скидки).

    @Override
    public boolean isSpecial() {
        return false;  // Обычный продукт
    }

    //Переопределение: простое строковое представление с ценой.

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
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

    //Переопределение: строковое представление для вывода (теперь добавлено!).

    @Override
    public String getStringRepresentation() {
        return getName() + " — " + getContentType() + " [simple: " + getPrice() + "]";
    }

    //Переопределение: имя от super.

    @Override
    public String getName() {
        return super.getName();
    }
}