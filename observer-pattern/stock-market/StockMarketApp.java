import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface StockMarketTicker {
    void subscribe(StockObserver observer);
    void unsubscribe(StockObserver observer);
    void notifyObservers(String stock, double price);
}

// Concrete Subject Class
class StockMarket implements StockMarketTicker {
    private List<StockObserver> observers = new ArrayList<>();

    @Override
    public void subscribe(StockObserver observer) {
        observers.add(observer);
        System.out.println(observer.getName() + " has subscribed to stock updates.");
    }

    @Override
    public void unsubscribe(StockObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getName() + " has unsubscribed from stock updates.");
    }

    @Override
    public void notifyObservers(String stock, double price) {
        for (StockObserver observer : observers) {
            observer.update(stock, price);
        }
    }

    public void updateStockPrice(String stock, double price) {
        System.out.println("Stock Update: " + stock + " is now $" + price);
        notifyObservers(stock, price);
    }
}

// Observer Interface
interface StockObserver {
    void update(String stock, double price);
    String getName();
}

// Concrete Observer Class
class ConcreteStockObserver implements StockObserver {
    private String name;

    public ConcreteStockObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String stock, double price) {
        System.out.println(name + " received stock update: " + stock + " is now $" + price);
    }

    @Override
    public String getName() {
        return name;
    }
}

// Client Code
public class StockMarketApp {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        StockObserver observer1 = new ConcreteStockObserver("Alice");
        StockObserver observer2 = new ConcreteStockObserver("Bob");
        StockObserver observer3 = new ConcreteStockObserver("Charlie");

        stockMarket.subscribe(observer1);
        stockMarket.subscribe(observer2);

        stockMarket.updateStockPrice("AAPL", 150.25);

        stockMarket.unsubscribe(observer1);

        stockMarket.updateStockPrice("GOOGL", 2800.50);

        stockMarket.subscribe(observer3);
        stockMarket.updateStockPrice("AMZN", 3450.75);
    }
}

/**
Explanation:
1. `StockMarketTicker` is the subject interface that defines methods for subscribing, unsubscribing, and notifying observers.
2. `StockMarket` is the concrete subject that maintains a list of observers and notifies them when a stock price changes.
3. `StockObserver` is the observer interface that defines the `update()` method for receiving stock updates.
4. `ConcreteStockObserver` is the concrete observer that implements the `update()` method to receive stock price notifications.
5. `StockMarketApp` is the client that creates the stock market, subscribes users, and updates stock prices.
*/
