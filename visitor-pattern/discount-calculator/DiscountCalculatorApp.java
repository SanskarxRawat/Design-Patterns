import java.util.ArrayList;
import java.util.List;

// Visitor Pattern - Applying Discounts on Different Items
interface ItemElement {
    void accept(DiscountVisitor visitor);
}

class Electronics implements ItemElement {
    private double price;

    public Electronics(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(DiscountVisitor visitor) {
        visitor.visit(this);
    }
}

class Clothing implements ItemElement {
    private double price;

    public Clothing(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(DiscountVisitor visitor) {
        visitor.visit(this);
    }
}

interface DiscountVisitor {
    void visit(Electronics electronics);
    void visit(Clothing clothing);
}

class PercentageDiscountVisitor implements DiscountVisitor {
    private double discountRate;

    public PercentageDiscountVisitor(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public void visit(Electronics electronics) {
        double discountedPrice = electronics.getPrice() * (1 - discountRate / 100);
        System.out.println("Electronics discounted price: " + discountedPrice);
    }

    @Override
    public void visit(Clothing clothing) {
        double discountedPrice = clothing.getPrice() * (1 - discountRate / 100);
        System.out.println("Clothing discounted price: " + discountedPrice);
    }
}

class FixedAmountDiscountVisitor implements DiscountVisitor {
    private double discountAmount;

    public FixedAmountDiscountVisitor(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public void visit(Electronics electronics) {
        double discountedPrice = electronics.getPrice() - discountAmount;
        System.out.println("Electronics discounted price: " + discountedPrice);
    }

    @Override
    public void visit(Clothing clothing) {
        double discountedPrice = clothing.getPrice() - discountAmount;
        System.out.println("Clothing discounted price: " + discountedPrice);
    }
}

// Strategy Pattern - Switching Discount Types
interface DiscountStrategies {
    void applyDiscount(List<ItemElement> items);
}

class PercentageDiscountStrategy implements DiscountStrategies {
    private double discountRate;

    public PercentageDiscountStrategy(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public void applyDiscount(List<ItemElement> items) {
        DiscountVisitor visitor = new PercentageDiscountVisitor(discountRate);
        for (ItemElement item : items) {
            item.accept(visitor);
        }
    }
}

class FixedAmountDiscountStrategy implements DiscountStrategies {
    private double discountAmount;

    public FixedAmountDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public void applyDiscount(List<ItemElement> items) {
        DiscountVisitor visitor = new FixedAmountDiscountVisitor(discountAmount);
        for (ItemElement item : items) {
            item.accept(visitor);
        }
    }
}

class DiscountContext {
    private DiscountStrategies strategy;

    public void setStrategy(DiscountStrategies strategy) {
        this.strategy = strategy;
    }

    public void applyDiscount(List<ItemElement> items) {
        if (strategy != null) {
            strategy.applyDiscount(items);
        } else {
            System.out.println("No discount strategy set.");
        }
    }
}

// Client Code
public class DiscountCalculatorApp {
    public static void main(String[] args) {
        List<ItemElement> items = new ArrayList<>();
        items.add(new Electronics(1000));
        items.add(new Clothing(200));

        DiscountContext discountContext = new DiscountContext();

        System.out.println("Applying Percentage Discount:");
        discountContext.setStrategy(new PercentageDiscountStrategy(10));
        discountContext.applyDiscount(items);

        System.out.println("\nApplying Fixed Amount Discount:");
        discountContext.setStrategy(new FixedAmountDiscountStrategy(50));
        discountContext.applyDiscount(items);
    }
}

/**
Explanation:
1. `ItemElement` is the element interface for the Visitor pattern that defines the `accept()` method for accepting visitors.
2. `Electronics` and `Clothing` are concrete elements that represent different items and accept visitors.
3. `DiscountVisitor` is the visitor interface that defines the `visit()` methods for applying discounts to different items.
4. `PercentageDiscountVisitor` and `FixedAmountDiscountVisitor` are concrete visitors that implement discount logic for each item type.
5. `DiscountStrategy` is the strategy interface that defines the `applyDiscount()` method for applying discounts to a list of items.
6. `PercentageDiscountStrategy` and `FixedAmountDiscountStrategy` are concrete strategies that implement different discount types.
7. `DiscountContext` is the context class that maintains a reference to a `DiscountStrategy` and uses it to apply discounts.
8. `DiscountCalculatorApp` is the client code that demonstrates using both the Visitor pattern for applying discounts on different items and the Strategy pattern for switching between discount types.
*/
