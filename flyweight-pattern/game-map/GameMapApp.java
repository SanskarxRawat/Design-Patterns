import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Pattern: Design a "Game Map" where tree objects are reused to represent a forest with minimal memory.
 */


// Flyweight Interface
interface Tree {
    void display(int x, int y);
}

// Concrete Flyweight Class
class ConcreteTree implements Tree {
    private String type;
    private String color;

    public ConcreteTree(String type, String color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying a " + type + " tree of color " + color + " at coordinates: (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class TreeFactory {
    private Map<String, Tree> treePool = new HashMap<>();

    public Tree getTree(String type, String color) {
        String key = type + "-" + color;
        Tree tree = treePool.get(key);
        if (tree == null) {
            tree = new ConcreteTree(type, color);
            treePool.put(key, tree);
        }
        return tree;
    }
}

// Client Code
public class GameMapApp {
    public static void main(String[] args) {
        TreeFactory treeFactory = new TreeFactory();

        // Creating a forest with multiple tree instances reused
        Tree oakTree = treeFactory.getTree("Oak", "Green");
        Tree pineTree = treeFactory.getTree("Pine", "Dark Green");
        Tree birchTree = treeFactory.getTree("Birch", "Yellow");

        oakTree.display(10, 20);
        oakTree.display(15, 25);

        pineTree.display(30, 40);
        pineTree.display(35, 45);

        birchTree.display(50, 60);
        birchTree.display(55, 65);
    }
}

/*
Explanation:
1. `Tree` is the flyweight interface that defines the method `display()` to show the tree at a specific coordinate.
2. `ConcreteTree` is the concrete flyweight class that represents an individual tree.
3. `TreeFactory` is the flyweight factory that manages the shared instances of `ConcreteTree` to minimize memory usage.
4. `GameMapApp` is the client that uses the `TreeFactory` to create and display trees efficiently, demonstrating how the Flyweight pattern can save memory when representing a large forest.
*/
