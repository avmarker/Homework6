package homework7;


import myMagazin.Product;

public class DiscountProduct extends Product {
    private double discount; // значение скидки (0,2-20%)
    private String endDate; // срок окончания скидки
//Конструктор
    public DiscountProduct(String name, double price, double discount, String endDate) {
        super(name, price);// вызываем конструктор базового класа Produkt
        setDiscount(discount); //устанавливаем скидку
        setEndDate(endDate); // устанавливаем срок действия
    }
// Сеттер для скидки с проверкой
    public void setDiscount(double discount) {
        if (discount <= 0 || discount >=1) {
            throw new IllegalArgumentException("Размер скидки должен быть между 0 и 1");
        }
        this.discount = discount;
    }
// Геттер для скидки

    public double getDiscount() {
        return discount;
    }
// Метод для получения цены с учетом скидки
    public double getDiscountedPrice() {
        return getPrice() * (1 - discount);
    }
// Сеттер для даты окончания скидки
    public void setEndDate(String endDate) {
        this.endDate = endDate;// можно добавить проверку формата даты, но для примера просто строка
    }

    public String getEndDate() {
        return endDate;
    }
// Переопределяем toString() для вывода информации
    @Override
    public String toString() {
        return super.toString() + "со скидкой" + (int)(discount*100) + "%, действует до" + endDate;
    }
}
