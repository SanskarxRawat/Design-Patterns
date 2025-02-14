# Single Responsibility Principle

A class should have just one reason to change.

- Try to make every class responsible for a **single part** of the functionality provided by the software.
- Ensure that responsibility is entirely **encapsulated** (you can also say *hidden within*) the class.

---

# Open/Closed Principle

**Classes should be open for extension but closed for modification.**

The main idea of this principle is to keep existing code from breaking when you implement new features.

- A class is **open** if you can extend it, produce a subclass, and do whatever you want with it—add new methods or fields, override base behavior, etc.
- Some programming languages let you restrict further extension of a class with special keywords, such as `final`. After this, the class is no longer open.
- At the same time, a class is **closed** (you can also say *complete*) if it’s 100% ready to be used by other classes—its interface is clearly defined and won’t be changed in the future.

## Key Points:
1. **Open for Extension**: You should be able to add new features or behaviors to a class without modifying its existing code.
2. **Closed for Modification**: The existing code should remain unchanged to avoid introducing bugs or breaking existing functionality.

## Example in Java

### Violation of Open/Closed Principle
Here’s an example where the principle is violated because adding a new shape requires modifying the existing `AreaCalculator` class.

```java
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.getWidth() * rectangle.getHeight();
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.getRadius() * circle.getRadius();
        }
        throw new IllegalArgumentException("Unknown shape");
    }
}

class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
```

Adhering to Open/Closed Principle
To adhere to the Open/Closed Principle, we can use abstraction (e.g., an interface or abstract class) to allow extension without modifying existing code.

```java
interface Shape {
    double calculateArea();
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}
```

---
# Liskov Substitution Principle

**When extending a class, remember that you should be able to pass objects of the subclass in place of objects of the parent class without breaking the client code.**

This means that the subclass should remain **compatible** with the behavior of the superclass. When overriding a method, extend the base behavior rather than replacing it with something else entirely.

## Key Points:
1. **Substitutability**: A subclass should be able to replace its superclass without affecting the correctness of the program.
2. **Behavioral Compatibility**: The subclass should adhere to the contract defined by the superclass, ensuring that it doesn’t violate the expected behavior.
3. **Avoid Overriding with Incompatible Logic**: When overriding methods, the subclass should extend or refine the behavior, not replace it with entirely different logic.

---

## Example in Java

### Violation of Liskov Substitution Principle
Here’s an example where the principle is violated because the subclass (`Square`) changes the behavior of the superclass (`Rectangle`), making them incompatible.

```java
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int calculateArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // Violation: Changing height to match width
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        this.width = height; // Violation: Changing width to match height
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Square();
        rectangle.setWidth(5);
        rectangle.setHeight(10);

        // Expected area: 50 (5 * 10)
        // Actual area: 100 (10 * 10) due to Square's behavior
        System.out.println("Area: " + rectangle.calculateArea());
    }
}
```
To adhere to LSP, we can redesign the classes to ensure that subclasses do not alter the behavior of the superclass.

```java
abstract class Shape {
    public abstract int calculateArea();
}

class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int calculateArea() {
        return width * height;
    }
}

class Square extends Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int calculateArea() {
        return side * side;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 10);
        Shape square = new Square(10);

        System.out.println("Rectangle Area: " + rectangle.calculateArea()); // Output: 50
        System.out.println("Square Area: " + square.calculateArea());       // Output: 100
    }
}
```
---

# Dependency Inversion Principle

**High-level classes shouldn’t depend on low-level classes. Both should depend on abstractions. Abstractions shouldn’t depend on details. Details should depend on abstractions.**

When designing software, you can distinguish between two levels of classes:
- **Low-level classes**: Implement basic operations such as working with a disk, transferring data over a network, connecting to a database, etc.
- **High-level classes**: Contain complex business logic that directs low-level classes to perform tasks.

The Dependency Inversion Principle ensures that high-level modules (business logic) are not tightly coupled to low-level modules (implementation details). Instead, both should depend on abstractions (e.g., interfaces or abstract classes).

---

## Key Points:
1. **High-level modules should not depend on low-level modules. Both should depend on abstractions.**
2. **Abstractions should not depend on details. Details should depend on abstractions.**
3. **Decoupling**: By depending on abstractions, the system becomes more flexible, maintainable, and easier to extend.

---

## Example in Java

### Violation of Dependency Inversion Principle
Here’s an example where the principle is violated because the high-level class (`ReportGenerator`) directly depends on the low-level class (`DatabaseService`).

```java
// Low-level class
class DatabaseService {
    public void saveReport(String reportData) {
        System.out.println("Saving report to database: " + reportData);
    }
}

// High-level class
class ReportGenerator {
    private DatabaseService databaseService;

    public ReportGenerator() {
        this.databaseService = new DatabaseService(); // Direct dependency on low-level class
    }

    public void generateReport(String data) {
        // Business logic to generate report
        System.out.println("Generating report: " + data);
        databaseService.saveReport(data);
    }
}

public class Main {
    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport("Sales Report");
    }
}
```

To adhere to DIP, we introduce an abstraction (interface) that both high-level and low-level classes depend on.

```java
// Abstraction (interface)
interface ReportSaver {
    void save(String reportData);
}

// Low-level class implementing the abstraction
class DatabaseService implements ReportSaver {
    @Override
    public void save(String reportData) {
        System.out.println("Saving report to database: " + reportData);
    }
}

// Another low-level class implementing the abstraction
class FileService implements ReportSaver {
    @Override
    public void save(String reportData) {
        System.out.println("Saving report to file: " + reportData);
    }
}

// High-level class depending on abstraction
class ReportGenerator {
    private ReportSaver reportSaver;

    // Dependency injection through constructor
    public ReportGenerator(ReportSaver reportSaver) {
        this.reportSaver = reportSaver;
    }

    public void generateReport(String data) {
        // Business logic to generate report
        System.out.println("Generating report: " + data);
        reportSaver.save(data);
    }
}

public class Main {
    public static void main(String[] args) {
        // Using DatabaseService
        ReportSaver databaseSaver = new DatabaseService();
        ReportGenerator reportGenerator1 = new ReportGenerator(databaseSaver);
        reportGenerator1.generateReport("Sales Report");

        // Using FileService
        ReportSaver fileSaver = new FileService();
        ReportGenerator reportGenerator2 = new ReportGenerator(fileSaver);
        reportGenerator2.generateReport("Inventory Report");
    }
}
```
---
**_This content is taken from work of Alexander Shvets, the author of the book Dive Into Design Patterns and the online course Dive Into Refactoring._**