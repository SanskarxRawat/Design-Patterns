// Abstract Class
abstract class OrderProcessing {
    // Template Method
    public final void processOrder() {
        validateOrder();
        packOrder();
        shipOrder();
    }

    // Steps to be implemented by subclasses
    protected abstract void validateOrder();
    protected abstract void packOrder();
    protected abstract void shipOrder();
}

// Concrete Class for Electronics
class ElectronicsOrder extends OrderProcessing {
    @Override
    protected void validateOrder() {
        System.out.println("Validating electronics order - checking stock and warranty.");
    }

    @Override
    protected void packOrder() {
        System.out.println("Packing electronics order - using anti-static packaging.");
    }

    @Override
    protected void shipOrder() {
        System.out.println("Shipping electronics order - using insured delivery.");
    }
}

// Concrete Class for Clothing
class ClothingOrder extends OrderProcessing {
    @Override
    protected void validateOrder() {
        System.out.println("Validating clothing order - checking size and availability.");
    }

    @Override
    protected void packOrder() {
        System.out.println("Packing clothing order - folding and wrapping in tissue paper.");
    }

    @Override
    protected void shipOrder() {
        System.out.println("Shipping clothing order - using standard delivery.");
    }
}

// Concrete Class for Groceries
class GroceryOrder extends OrderProcessing {
    @Override
    protected void validateOrder() {
        System.out.println("Validating grocery order - checking freshness and expiry dates.");
    }

    @Override
    protected void packOrder() {
        System.out.println("Packing grocery order - using temperature-controlled packaging.");
    }

    @Override
    protected void shipOrder() {
        System.out.println("Shipping grocery order - using same-day delivery.");
    }
}

// Client Code
public class EcommerceOrderApp {
    public static void main(String[] args) {
        OrderProcessing electronicsOrder = new ElectronicsOrder();
        OrderProcessing clothingOrder = new ClothingOrder();
        OrderProcessing groceryOrder = new GroceryOrder();

        System.out.println("Processing Electronics Order:");
        electronicsOrder.processOrder();

        System.out.println("\nProcessing Clothing Order:");
        clothingOrder.processOrder();

        System.out.println("\nProcessing Grocery Order:");
        groceryOrder.processOrder();
    }
}

/**
Explanation:
1. `OrderProcessing` is the abstract class that defines the template method `processOrder()`, which outlines the steps to process an order.
2. `ElectronicsOrder`, `ClothingOrder`, and `GroceryOrder` are concrete classes that implement the specific steps for validating, packing, and shipping each type of product.
3. `EcommerceOrderApp` is the client that creates instances of different orders and processes them using the template method.
*/
