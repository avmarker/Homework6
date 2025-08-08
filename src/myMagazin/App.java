package myMagazin;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Person person = null;
//  Ввод данных покупателя
        while (true) {
            System.out.print("Введите имя покупателя: ");
            String name = sc.nextLine().trim();
            try {
                if (name.isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                    continue;
                }
                if (name.length() < 3) {
                    System.out.println("Имя не может быть короче 3 символов");
                    continue;
                }
                System.out.print("Введите сумму денег: ");
                String moneyStr = sc.nextLine().trim();
                double money = Double.parseDouble(moneyStr);
                if (money < 0) {
                    System.out.println("Деньги не могут быть отрицательными ");
                    continue;
                }
                person = new Person(name, money);
                break;

            }   catch (NumberFormatException e) {
                   System.out.println("Некорректный формат суммы");
            }   catch (IllegalArgumentException e) {
                   System.out.println(e.getMessage());
            }
        }
// Ввод продуктов
        List<Product> products = new ArrayList<>();
        while (true) {
            System.out.print("Введите название продукта или END: ");
            String prodName = sc.nextLine().trim();
            if (prodName.equalsIgnoreCase("END")) break;

            if (prodName.isEmpty()) {
                System.out.println("Название продукта не может быть пустым");
                continue;
            }

            System.out.print("Введите стоимость продукта: ");
            String priceStr = sc.nextLine().trim();
            try {
                double price = Double.parseDouble(priceStr);
                if (price < 0) {
                    System.out.println("Стоимость не может быть отрицательной");
                    continue;
                }
                Product p = new Product(prodName, price);
                products.add(p);
            } catch (NumberFormatException e) {
                System.out.println("Некоректный формат стоимости");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
//  Покупки

        boolean boughtSome = false;
        for (Product p : products) {
            if (person.buyProduct(p)) {
                System.out.println(person.getName() + " купил " + p.getName());
                boughtSome = true;
            } else {
                System.out.println(person.getName() + " не может позволит себе " + p.getName());
            }
        }
//  Итог
        if (boughtSome) {
            System.out.println(person.toString()); // список покупок
        } else {
            System.out.println(person.getName() + " - Ничего не куплено");
        }
        sc.close();
    }

}
