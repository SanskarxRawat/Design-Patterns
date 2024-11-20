// Element Interface
interface Items {
    void accept(TaxVisitor visitor);
}

// Concrete Elements
class FoodItem implements Items {
    private double price;

    public FoodItem(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visit(this);
    }
}

class ElectronicsItem implements Items {
    private double price;

    public ElectronicsItem(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor Interface
interface TaxVisitor {
    void visit(FoodItem food);
    void visit(ElectronicsItem electronics);
}

// Concrete Visitor Class
class TaxCalculator implements TaxVisitor {
    @Override
    public void visit(FoodItem food) {
        double tax = food.getPrice() * 0.05; // 5% tax for food items
        System.out.println("Food item tax: $" + tax);
    }

    @Override
    public void visit(ElectronicsItem electronics) {
        double tax = electronics.getPrice() * 0.15; // 15% tax for electronics
        System.out.println("Electronics item tax: $" + tax);
    }
}

// Client Code
public class TaxCalculationApp {
    public static void main(String[] args) {
        Items food = new FoodItem(100);
        Items electronics = new ElectronicsItem(200);

        TaxVisitor taxCalculator = new TaxCalculator();

        System.out.println("Calculating tax for food:");
        food.accept(taxCalculator);

        System.out.println("\nCalculating tax for electronics:");
        electronics.accept(taxCalculator);
    }
}

/**
Explanation:
1. `Item` is the element interface that declares the `accept()` method for accepting visitors.
2. `FoodItem` and `ElectronicsItem` are concrete elements that implement the `accept()` method to accept visitors.
3. `TaxVisitor` is the visitor interface that declares the `visit()` methods for different item types.
4. `TaxCalculator` is the concrete visitor that calculates tax for different item types.
5. `TaxCalculationApp` is the client that creates items and uses the visitor to calculate their tax.
*/
