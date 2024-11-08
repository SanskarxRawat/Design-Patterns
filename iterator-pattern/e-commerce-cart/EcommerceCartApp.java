import java.util.ArrayList;
import java.util.List;

/**
 * Iterator Pattern: Create an "E-commerce Cart Iterator" that iterates over items in a shopping cart.
 */
// Item Class
class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Aggregate Interface
interface Cart {
    Iterator getIterator();
}

// Concrete Aggregate Class
class ShoppingCart implements Cart {
    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public Iterator getIterator() {
        return new CartIterator(items);
    }
}

// Iterator Interface
interface Iterator {
    boolean hasNext();
    Item next();
}

// Concrete Iterator Class
class CartIterator implements Iterator {
    private List<Item> items;
    private int position;

    public CartIterator(List<Item> items) {
        this.items = items;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public Item next() {
        if (this.hasNext()) {
            return items.get(position++);
        }
        return null;
    }
}

// Client Code
public class EcommerceCartApp {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Laptop", 1200.00));
        cart.addItem(new Item("Headphones", 150.00));
        cart.addItem(new Item("Mouse", 25.00));

        Iterator iterator = cart.getIterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            System.out.println("Item: " + item.getName() + ", Price: $" + item.getPrice());
        }
    }
}

/**
Explanation:
1. `Item` is a simple class that represents an item in the shopping cart with a name and price.
2. `Cart` is the aggregate interface that defines a method `getIterator()` for getting an iterator.
3. `ShoppingCart` is the concrete aggregate class that contains a list of items and provides an iterator to iterate over them.
4. `Iterator` is the iterator interface that defines methods `hasNext()` and `next()` for iteration.
5. `CartIterator` is the concrete iterator class that iterates over the items in the shopping cart.
6. `EcommerceCartApp` is the client that creates a shopping cart, adds items to it, and iterates over them using the iterator pattern.
*/
