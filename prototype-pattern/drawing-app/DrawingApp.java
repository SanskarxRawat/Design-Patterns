import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Pattern: Implement a "Prototype-based Drawing App" where users can duplicate shapes and customize their properties.
 */
// Prototype Interface
abstract class Shape implements Cloneable {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract void draw();
}

// Concrete Prototype Classes
class Circle extends Shape {
    public Circle() {
        setType("Circle");
    }

    @Override
    void draw() {
        System.out.println("Drawing a Circle...");
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        setType("Rectangle");
    }

    @Override
    void draw() {
        System.out.println("Drawing a Rectangle...");
    }
}

// Prototype Registry
class ShapeRegistry {
    private Map<String, Shape> shapeMap = new HashMap<>();

    public ShapeRegistry() {
        loadShapes();
    }

    public Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (cachedShape != null) ? cachedShape.clone() : null;
    }

    private void loadShapes() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}

// Client Code
public class DrawingApp {
    public static void main(String[] args) {
        ShapeRegistry shapeRegistry = new ShapeRegistry();

        Shape clonedCircle = shapeRegistry.getShape("1");
        System.out.println("Cloned Shape: " + clonedCircle.getType());
        clonedCircle.draw();

        Shape clonedRectangle = shapeRegistry.getShape("2");
        System.out.println("Cloned Shape: " + clonedRectangle.getType());
        clonedRectangle.draw();

        // Customizing cloned shapes
        Shape customizedCircle = clonedCircle.clone();
        customizedCircle.setId("3");
        System.out.println("Customized Shape: " + customizedCircle.getType());
        customizedCircle.draw();
    }
}

/**
Explanation:
1. `Shape` is the prototype interface that defines a `clone()` method for creating copies of shapes.
2. `Circle` and `Rectangle` are concrete classes that extend `Shape` and implement the `draw()` method.
3. `ShapeRegistry` acts as a registry for storing and retrieving shape prototypes. It provides a `getShape()` method that returns a cloned shape.
4. `DrawingApp` is the client that uses the `ShapeRegistry` to create and customize shapes.
*/
