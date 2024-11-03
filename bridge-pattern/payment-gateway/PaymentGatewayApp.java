/**
 * Bridge Pattern: Implement a "Payment Gateway" where different payment methods (Credit Card, PayPal) are separated from various banks.
 */

// Implementor Interface
interface Bank {
    void processPayment(String paymentType, double amount);
}

// Concrete Implementors
class ChaseBank implements Bank {
    @Override
    public void processPayment(String paymentType, double amount) {
        System.out.println("Processing " + paymentType + " payment of $" + amount + " through Chase Bank.");
    }
}

class CitiBank implements Bank {
    @Override
    public void processPayment(String paymentType, double amount) {
        System.out.println("Processing " + paymentType + " payment of $" + amount + " through Citi Bank.");
    }
}

// Abstraction
abstract class PaymentMethod {
    protected Bank bank;

    public PaymentMethod(Bank bank) {
        this.bank = bank;
    }

    abstract void makePayment(double amount);
}

// Refined Abstractions
class CreditCardPayment extends PaymentMethod {
    public CreditCardPayment(Bank bank) {
        super(bank);
    }

    @Override
    void makePayment(double amount) {
        bank.processPayment("Credit Card", amount);
    }
}

class PayPalPayment extends PaymentMethod {
    public PayPalPayment(Bank bank) {
        super(bank);
    }

    @Override
    void makePayment(double amount) {
        bank.processPayment("PayPal", amount);
    }
}

// Client Code
public class PaymentGatewayApp {
    public static void main(String[] args) {
        Bank chaseBank = new ChaseBank();
        PaymentMethod creditCardPayment = new CreditCardPayment(chaseBank);
        creditCardPayment.makePayment(250.75);

        System.out.println();

        Bank citiBank = new CitiBank();
        PaymentMethod paypalPayment = new PayPalPayment(citiBank);
        paypalPayment.makePayment(100.50);
    }
}

/**
Explanation:
1. `Bank` is the implementor interface that defines the method `processPayment()` for processing payments through different banks.
2. `ChaseBank` and `CitiBank` are concrete implementors that provide specific implementations for payment processing.
3. `PaymentMethod` is the abstraction that defines high-level operations for making payments, associating with a `Bank`.
4. `CreditCardPayment` and `PayPalPayment` are refined abstractions that provide concrete implementations for different payment methods.
5. `PaymentGatewayApp` is the client that uses the payment methods to make payments through different banks in a unified manner.
*/
