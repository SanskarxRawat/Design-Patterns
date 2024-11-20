// Decorator Pattern - Adding Fees
interface Payment {
    double getAmount();
    String getDescription();
}

class BasePayment implements Payment {
    private double amount;

    public BasePayment(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return "Base payment of $" + amount;
    }
}

// Decorator for Additional Fees
abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;

    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }

    @Override
    public double getAmount() {
        return decoratedPayment.getAmount();
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription();
    }
}

class ProcessingFeeDecorator extends PaymentDecorator {
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public double getAmount() {
        return decoratedPayment.getAmount() + 5.00; // Add a $5 processing fee
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + $5 processing fee";
    }
}

class TaxDecorator extends PaymentDecorator {
    public TaxDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public double getAmount() {
        return decoratedPayment.getAmount() * 1.10; // Add 10% tax
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + 10% tax";
    }
}

// Adapter Pattern - Integrating Payment Gateways
interface PaymentGateway {
    void processPayment(double amount);
}

class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}

class PayPalAdapter implements PaymentGateway {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

class StripeGateway {
    public void executePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}

class StripeAdapter implements PaymentGateway {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripeGateway.executePayment(amount);
    }
}

// Client Code
public class PaymentProcessorApp {
    public static void main(String[] args) {
        // Decorator Pattern - Adding Fees
        Payment basePayment = new BasePayment(100.00);
        Payment paymentWithProcessingFee = new ProcessingFeeDecorator(basePayment);
        Payment finalPayment = new TaxDecorator(paymentWithProcessingFee);

        System.out.println(finalPayment.getDescription());
        System.out.println("Total Amount: $" + finalPayment.getAmount());

        // Adapter Pattern - Integrating Payment Gateways
        PaymentGateway payPalGateway = new PayPalAdapter(new PayPalGateway());
        PaymentGateway stripeGateway = new StripeAdapter(new StripeGateway());

        System.out.println("\nProcessing payments:");
        payPalGateway.processPayment(finalPayment.getAmount());
        stripeGateway.processPayment(finalPayment.getAmount());
    }
}

/**
Explanation:
1. `Payment` is the component interface for the Decorator pattern that defines the basic payment functionality.
2. `BasePayment` is the concrete component class that represents a simple payment.
3. `PaymentDecorator` is the abstract base class for adding additional fees to the payment.
4. `ProcessingFeeDecorator` and `TaxDecorator` are concrete decorators that add processing fees and tax to the payment.
5. `PaymentGateway` is the target interface for the Adapter pattern that defines the method `processPayment()`.
6. `PayPalGateway` and `StripeGateway` are existing payment gateway classes that need to be integrated.
7. `PayPalAdapter` and `StripeAdapter` adapt the existing gateways to work with the `PaymentGateway` interface.
8. `PaymentProcessorApp` is the client code that demonstrates using both the Decorator and Adapter patterns for payment processing.
*/
