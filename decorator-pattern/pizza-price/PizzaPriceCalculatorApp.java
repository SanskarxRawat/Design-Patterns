/**
 * Decorator Pattern: Build a "Pizza Price Calculator" that calculates the price based on toppings (cheese, pepperoni, mushrooms).
 */

// Component Interface
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}

// Abstract Decorator Class
abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

// Concrete Decorators
class Cheese extends ToppingDecorator {
    public Cheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.25;
    }
}

class Pepperoni extends ToppingDecorator {
    public Pepperoni(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.75;
    }
}

class Mushrooms extends ToppingDecorator {
    public Mushrooms(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushrooms";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50;
    }
}

// Client Code
public class PizzaPriceCalculatorApp {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        System.out.println(pizza.getDescription() + " - Cost: $" + pizza.getCost());

        pizza = new Cheese(pizza);
        System.out.println(pizza.getDescription() + " - Cost: $" + pizza.getCost());

        pizza = new Pepperoni(pizza);
        System.out.println(pizza.getDescription() + " - Cost: $" + pizza.getCost());

        pizza = new Mushrooms(pizza);
        System.out.println(pizza.getDescription() + " - Cost: $" + pizza.getCost());
    }
}

/**
Explanation:
1. `Pizza` is the component interface that defines the methods `getDescription()` and `getCost()`.
2. `PlainPizza` is the concrete component that represents the basic pizza without any toppings.
3. `ToppingDecorator` is the abstract decorator class that implements `Pizza` and delegates its methods to the component it decorates.
4. `Cheese`, `Pepperoni`, and `Mushrooms` are concrete decorators that add specific toppings and costs to the pizza.
5. `PizzaPriceCalculatorApp` is the client that creates a pizza and adds toppings using the decorator pattern.
*/
