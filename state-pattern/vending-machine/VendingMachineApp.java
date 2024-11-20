// State Interface
interface VendingMachineState {
    void handleState(VendingMachineContext context);
}

// Concrete State Classes
class SelectItemState implements VendingMachineState {
    @Override
    public void handleState(VendingMachineContext context) {
        System.out.println("Item Selected - Please proceed with payment");
        context.setState(new PaymentState());
    }
}

class PaymentState implements VendingMachineState {
    @Override
    public void handleState(VendingMachineContext context) {
        System.out.println("Payment Received - Dispensing item");
        context.setState(new DispenseItemState());
    }
}

class DispenseItemState implements VendingMachineState {
    @Override
    public void handleState(VendingMachineContext context) {
        System.out.println("Item Dispensed - Thank you for your purchase");
        context.setState(new SelectItemState());
    }
}

// Context Class
class VendingMachineContext {
    private VendingMachineState state;

    public VendingMachineContext() {
        // Initial state is Select Item
        state = new SelectItemState();
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void proceed() {
        state.handleState(this);
    }
}

// Client Code
public class VendingMachineApp {
    public static void main(String[] args) {
        VendingMachineContext vendingMachine = new VendingMachineContext();

        // Simulate the vending machine process
        vendingMachine.proceed(); // Select item
        vendingMachine.proceed(); // Payment
        vendingMachine.proceed(); // Dispense item
    }
}

/**
Explanation:
1. `VendingMachineState` is the state interface that defines the method `handleState()` for transitioning between states.
2. `SelectItemState`, `PaymentState`, and `DispenseItemState` are concrete state classes that implement the behavior for each state of the vending machine.
3. `VendingMachineContext` is the context class that maintains a reference to the current state and delegates state transitions.
4. `VendingMachineApp` is the client that simulates the vending machine process through different states.
*/
