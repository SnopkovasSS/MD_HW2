package org.skypro.skyshop.product;


//Интерфейс для продуктов (getName, getPrice, isSpecial для filter в Basket).

public interface Product {
    String getName();
    double getPrice();
    boolean isSpecial();
}