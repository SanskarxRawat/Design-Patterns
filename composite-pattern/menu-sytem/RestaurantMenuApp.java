import java.util.ArrayList;
import java.util.List;

// Component Interface
interface MenuItem {
    void showDetails();
    double getPrice();
}

// Leaf Class
class Dish implements MenuItem {
    private String name;
    private double price;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showDetails() {
        System.out.println("Dish: " + name + " - Price: $" + price);
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Composite Class
class Combo implements MenuItem {
    private String name;
    private List<MenuItem> items = new ArrayList<>();

    public Combo(String name) {
        this.name = name;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    @Override
    public void showDetails() {
        System.out.println("Combo: " + name);
        for (MenuItem item : items) {
            item.showDetails();
        }
        System.out.println("Total Combo Price: $" + getPrice());
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (MenuItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}

// Client Code
public class RestaurantMenuApp {
    public static void main(String[] args) {
        MenuItem burger = new Dish("Burger", 5.99);
        MenuItem fries = new Dish("Fries", 2.49);
        MenuItem drink = new Dish("Soft Drink", 1.99);

        Combo lunchCombo = new Combo("Lunch Combo");
        lunchCombo.addItem(burger);
        lunchCombo.addItem(fries);
        lunchCombo.addItem(drink);

        MenuItem pizza = new Dish("Pizza", 8.99);

        Combo familyCombo = new Combo("Family Combo");
        familyCombo.addItem(lunchCombo);
        familyCombo.addItem(pizza);

        System.out.println("--- Individual Items ---");
        burger.showDetails();
        pizza.showDetails();

        System.out.println("\n--- Combos ---");
        lunchCombo.showDetails();
        familyCombo.showDetails();
    }
}

/**
Explanation:
1. `MenuItem` is the component interface that defines methods `showDetails()` and `getPrice()` for both dishes and combos.
2. `Dish` is the leaf class that represents individual dishes and implements the `showDetails()` and `getPrice()` methods.
3. `Combo` is the composite class that can contain multiple dishes and/or combos. It implements `showDetails()` and calculates the total price of the combo.
4. `RestaurantMenuApp` is the client that creates dishes and combos and performs operations on the entire menu.
*/
