package org.skypro.skyshop.product;

//Продукт со скидкой. Наследует Product, implements Searchable.
 //Цена рассчитывается как базовая минус discount%.

public class DiscountedProduct extends Product {
    private int discount;  // Скидка в % (0-100)

    //Конструктор: устанавливает name, basePrice и discount.
    //@param name Название продукта.
    //@param basePrice Базовая цена (>0).
    //@param discount Скидка (0-100).
    //@throws IllegalArgumentException Если name null/blank, basePrice <=0, discount <0 или >100.

    public DiscountedProduct(String name, double basePrice, int discount) {
        super(name, basePrice);  // Устанавливаем базовую цену в super
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.discount = discount;
    }

    //Переопределение: специальный продукт, если скидка >0%.

    @Override
    public boolean isSpecial() {
        return discount > 0;  // Специальный, если есть скидка
    }

    //Переопределение: возвращает цену со скидкой.

    @Override
    public double getPrice() {
        return super.getPrice();
        // basePrice  (1 - discount%)
    }

    //Геттер для скидки.

    public int getDiscount() {
        return discount;
    }

    //Переопределение: строковое представление с базовой ценой и скидкой.

    @Override
    public String toString() {
        return getName() + ": " + super.getPrice() + " (" + discount + "% скидка) = " + getPrice();
    }

    //Переопределение: search term с discounted price.

    @Override
    public String getSearchTerm() {
        return getName() + " " + getPrice();  // Имя + цена со скидкой
    }

    //Переопределение: тип контента.

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    //Переопределение: строковое представление для поиска/вывод.

    @Override
    public String getStringRepresentation() {
        return getName() + " — " + getContentType() + " (discount: " + discount + "%, price: " + getPrice() + ")";
    }

    //Переопределение: имя от super.

    @Override
    public String getName() {
        return super.getName();
    }
}