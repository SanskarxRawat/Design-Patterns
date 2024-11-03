/**
 * Facade Pattern: Design an "Online Shopping Facade" that simplifies operations like product search, order placement, and payment.
 */

// Subsystem Class: Product Search
class ProductSearch {
    public void searchProduct(String productName) {
        System.out.println("Searching for product: " + productName);
    }
}

// Subsystem Class: Order Placement
class OrderPlacement {
    public void placeOrder(String productName) {
        System.out.println("Placing order for product: " + productName);
    }
}

// Subsystem Class: Payment Processing
class PaymentProcessing {
    public void processPayment(String paymentMethod, double amount) {
        System.out.println("Processing payment of $" + amount + " using " + paymentMethod);
    }
}

// Facade Class
class OnlineShoppingFacade {
    private ProductSearch productSearch;
    private OrderPlacement orderPlacement;
    private PaymentProcessing paymentProcessing;

    public OnlineShoppingFacade() {
        this.productSearch = new ProductSearch();
        this.orderPlacement = new OrderPlacement();
        this.paymentProcessing = new PaymentProcessing();
    }

    public void buyProduct(String productName, String paymentMethod, double amount) {
        productSearch.searchProduct(productName);
        orderPlacement.placeOrder(productName);
        paymentProcessing.processPayment(paymentMethod, amount);
        System.out.println("Order completed for: " + productName);
    }
}

// Client Code
public class OnlineShoppingApp {
    public static void main(String[] args) {
        OnlineShoppingFacade shoppingFacade = new OnlineShoppingFacade();
        shoppingFacade.buyProduct("Laptop", "Credit Card", 1200.00);
    }
}

/**
Explanation:
1. `ProductSearch`, `OrderPlacement`, and `PaymentProcessing` are subsystem classes that handle different parts of the online shopping process.
2. `OnlineShoppingFacade` is the facade class that provides a simplified interface to the client by combining the operations of product search, order placement, and payment processing.
3. `OnlineShoppingApp` is the client that interacts with the facade to complete the shopping process in a simplified way.
*/
