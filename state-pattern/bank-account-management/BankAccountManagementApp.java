// State Pattern - Bank Account Status
interface AccountState {
    void handleState(BankAccountContext context);
}

class ActiveState implements AccountState {
    @Override
    public void handleState(BankAccountContext context) {
        System.out.println("Account is active. All operations are available.");
    }
}

class ClosedState implements AccountState {
    @Override
    public void handleState(BankAccountContext context) {
        System.out.println("Account is closed. No operations are allowed.");
    }
}

class OverdrawnState implements AccountState {
    @Override
    public void handleState(BankAccountContext context) {
        System.out.println("Account is overdrawn. Deposits are allowed, but withdrawals are not.");
    }
}

class BankAccountContext {
    private AccountState state;

    public BankAccountContext() {
        state = new ActiveState();
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public void performOperation() {
        state.handleState(this);
    }
}

// Client Code
public class BankAccountManagementApp {
    public static void main(String[] args) {
        BankAccountContext account = new BankAccountContext();

        System.out.println("Initial Account State:");
        account.performOperation();

        System.out.println("\nSetting Account to Overdrawn State:");
        account.setState(new OverdrawnState());
        account.performOperation();

        System.out.println("\nSetting Account to Closed State:");
        account.setState(new ClosedState());
        account.performOperation();
    }
}

/**
Explanation:
1. `AccountState` is the state interface that defines the `handleState()` method for handling different account statuses.
2. `ActiveState`, `ClosedState`, and `OverdrawnState` are concrete state classes that implement behavior for each account status.
3. `BankAccountContext` maintains the current state of the bank account and performs operations based on the current state.
4. `BankAccountManagementApp` is the client code that demonstrates the use of the State pattern for managing different bank account statuses.
*/
