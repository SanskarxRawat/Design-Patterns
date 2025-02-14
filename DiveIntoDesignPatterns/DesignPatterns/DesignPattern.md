# Design Principle


## Encapsulate What Varies

### Overview
🛡️ **Encapsulate What Varies** is a foundational design principle that emphasizes isolating components of your code that are prone to change. By separating volatile parts from stable ones, you reduce the impact of future changes and improve maintainability.

## Key Idea
> *"Identify the aspects of your application that vary and separate them from what stays the same."*

The goal is to **minimize the effects of change** by localizing modifications to specific, well-defined modules. This promotes flexibility and reduces the risk of unintended side effects.

---

## Why Encapsulate What Varies?
- **Reduce Risk**: Changes to one part of the codebase won’t ripple through the entire system.
- **Improve Maintainability**: Isolate modifications to specific modules.
- **Enable Extensibility**: New variations can be added without altering existing code (Open/Closed Principle).

---

## How to Apply This Principle
1. **Identify Volatile Components**  
   Look for code that changes frequently (e.g., algorithms, UI elements, third-party integrations).
2. **Separate Concerns**  
   Extract varying behavior into separate classes, interfaces, or modules.
3. **Use Abstraction**  
   Hide implementation details behind interfaces or abstract classes.
4. **Favor Composition**  
   Combine stable components with interchangeable modules at runtime.

---

## Example: Payment Processing
```java
// Stable component: PaymentProcessor
public class PaymentProcessor {
    private PaymentGateway paymentGateway; // Varying component

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(double amount) {
        paymentGateway.executePayment(amount);
    }
}

// Varying implementations
interface PaymentGateway {
    void executePayment(double amount);
}

class PayPalGateway implements PaymentGateway { /* ... */ }
class StripeGateway implements PaymentGateway { /* ... */ }
class BankTransferGateway implements PaymentGateway { /* ... */ }

```

---
## Program to an Interface, not an Implementation

### Overview
🎯 **Program to an Interface, not an Implementation** is a core principle that promotes designing systems around abstractions rather than concrete implementations. By focusing on interfaces, you decouple your code from specific details, enabling flexibility, scalability, and easier maintenance.

## Key Idea
> *"Depend on abstractions, not on concrete classes."*

This principle ensures components interact through contracts (interfaces or abstract classes) rather than rigid, concrete implementations. This minimizes tight coupling and simplifies future changes.

---

## Why Program to an Interface?
- **Decouple Components**: Reduces dependencies between classes, making systems more modular.
- **Enhance Flexibility**: Swap implementations without altering dependent code.
- **Improve Testability**: Easily mock interfaces for unit testing.
- **Future-Proofing**: New implementations can be added without breaking existing logic.

---

## How to Apply This Principle
1. **Define Contracts First**  
   Create interfaces or abstract classes to represent behaviors or roles.
2. **Implement Abstractions**  
   Write concrete classes that adhere to these contracts.
3. **Inject Dependencies**  
   Use dependency injection to provide implementations at runtime.
4. **Avoid Concrete References**  
   Reference interfaces in client code instead of specific implementations.

---

## Example: Data Storage System
```java
// Interface (abstraction)
public interface DataStorage {
    void save(String data);
    String load();
}

// Concrete implementations
class DatabaseStorage implements DataStorage {
    public void save(String data) { /* Save to database */ }
    public String load() { /* Load from database */ }
}

class CloudStorage implements DataStorage {
    public void save(String data) { /* Save to cloud */ }
    public String load() { /* Load from cloud */ }
}

// Client code depends on the interface, not concrete classes
public class DataProcessor {
    private DataStorage storage;

    public DataProcessor(DataStorage storage) {
        this.storage = storage; // Works with ANY DataStorage implementation
    }

    public void processData(String data) {
        storage.save(data);
        // ...
    }
}
```
---

## Favor Composition Over Inheritance

### Overview
🔄 **Favor Composition Over Inheritance** advocates for building flexible and maintainable systems by combining objects (composition) rather than relying heavily on class hierarchies (inheritance). While inheritance is straightforward for code reuse, it often leads to rigid designs and unexpected complexity as systems evolve.

## Key Idea
> *"Inheritance is easy but risky; composition is flexible and resilient."*

Inheritance creates tight coupling between parent and child classes, while composition allows objects to delegate responsibilities to other objects dynamically.

---

## Why Favor Composition?
### Problems with Inheritance:
1. **Breaks Encapsulation**  
   Subclasses depend on parent class implementation details, making them fragile to changes in the parent.

2. **Tight Coupling**  
   Changes to the parent class can unintentionally break subclasses.

3. **Rigid Hierarchies**  
   Deep inheritance trees become hard to maintain and evolve over time.

4. **Overriding Complexity**  
   Subclassing often requires overriding methods, which can lead to unexpected behavior if not carefully managed.

5. **Limited Reusability**  
   Inheritance forces a "is-a" relationship, while composition supports "has-a" relationships that are more flexible.

---

## How to Apply Composition
1. **Identify Reusable Behaviors**  
   Extract varying behaviors into interfaces or abstract classes.
2. **Delegate Responsibilities**  
   Use object fields to hold instances of these behaviors (composition).
3. **Avoid Deep Hierarchies**  
   Replace subclassing with strategies, decorators, or other patterns.
4. **Use Dependency Injection**  
   Configure objects with specific behaviors at runtime.

---

## Example: Inheritance vs. Composition
### Inheritance Approach (Problematic)
```java
// Base class
class Duck {
    void quack() { System.out.println("Quack!"); }
    void swim() { System.out.println("Swim!"); }
    void fly() { System.out.println("Fly!"); } // Problem: Not all ducks fly!
}

// Subclass breaks LSP (Liskov Substitution Principle)
class RubberDuck extends Duck {
    @Override
    void fly() { throw new UnsupportedOperationException(); } // Forced override
}
```

### Composition (Solution)
```java
// Delegate flying behavior to an interface
interface FlyBehavior {
    void fly();
}

class FlyWithWings implements FlyBehavior {
    public void fly() { System.out.println("Fly!"); }
}

class NoFly implements FlyBehavior {
    public void fly() { System.out.println("Can't fly!"); }
}

// Compose behaviors
class Duck {
    private FlyBehavior flyBehavior;

    Duck(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    void performFly() {
        flyBehavior.fly(); // Delegation
    }
}

// Usage
Duck mallard = new Duck(new FlyWithWings());
Duck rubberDuck = new Duck(new NoFly());
```




