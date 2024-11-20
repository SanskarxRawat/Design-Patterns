// Strategy Interface
interface DiscountStrategy {
    double applyDiscount(double price);
}

// Concrete Strategy Classes
class PercentageDiscount implements DiscountStrategy {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double price) {
        return price - (price * percentage / 100);
    }
}

class FixedDiscount implements DiscountStrategy {
    private double discountAmount;

    public FixedDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double price) {
        return Math.max(price - discountAmount, 0);
    }
}

class SeasonalDiscount implements DiscountStrategy {
    private double seasonalFactor;

    public SeasonalDiscount(double seasonalFactor) {
        this.seasonalFactor = seasonalFactor;
    }

    @Override
    public double applyDiscount(double price) {
        return price * seasonalFactor;
    }
}

// Context Class
class Product {
    private String name;
    private double price;
    private DiscountStrategy discountStrategy;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void applyDiscount() {
        double discountedPrice = discountStrategy.applyDiscount(price);
        System.out.println("Product: " + name + ", Original Price: $" + price + ", Discounted Price: $" + discountedPrice);
    }
}

// Client Code
public class DiscountSystemApp {
    public static void main(String[] args) {
        Product product1 = new Product("Laptop", 1000.00);
        Product product2 = new Product("Smartphone", 500.00);
        Product product3 = new Product("Headphones", 150.00);

        // Apply Percentage Discount
        product1.setDiscountStrategy(new PercentageDiscount(10));
        product1.applyDiscount();

        // Apply Fixed Discount
        product2.setDiscountStrategy(new FixedDiscount(50));
        product2.applyDiscount();

        // Apply Seasonal Discount
        product3.setDiscountStrategy(new SeasonalDiscount(0.85)); // 15% off seasonal discount
        product3.applyDiscount();
    }
}

/**
Explanation:
1. `DiscountStrategy` is the strategy interface that defines the method `applyDiscount()` for calculating discounts.
2. `PercentageDiscount`, `FixedDiscount`, and `SeasonalDiscount` are concrete strategy classes that implement different discount strategies.
3. `Product` is the context class that maintains a reference to a `DiscountStrategy` and applies it to calculate the discounted price.
4. `DiscountSystemApp` is the client that creates products, sets discount strategies, and applies the discounts.
*/
