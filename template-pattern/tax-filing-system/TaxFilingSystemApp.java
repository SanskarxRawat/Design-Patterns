import java.util.ArrayList;
import java.util.List;

// Template Method Pattern - Tax Filing Methods
abstract class TaxFiling {
    // Template method
    public final void fileTax() {
        collectDocuments();
        calculateTax();
        submitTaxReturn();
        generateReceipt();
    }

    protected abstract void collectDocuments();
    protected abstract void calculateTax();

    protected void submitTaxReturn() {
        System.out.println("Submitting tax return to the tax authority.");
    }

    protected void generateReceipt() {
        System.out.println("Generating receipt for the tax filing.");
    }
}

class IndividualTaxFiling extends TaxFiling {
    @Override
    protected void collectDocuments() {
        System.out.println("Collecting documents for individual tax filing.");
    }

    @Override
    protected void calculateTax() {
        System.out.println("Calculating tax for an individual.");
    }
}

class CorporateTaxFiling extends TaxFiling {
    @Override
    protected void collectDocuments() {
        System.out.println("Collecting documents for corporate tax filing.");
    }

    @Override
    protected void calculateTax() {
        System.out.println("Calculating tax for a corporation.");
    }
}

// Visitor Pattern - Tax Calculations
interface TaxElement {
    void accept(TaxVisitors visitor);
}

class Income implements TaxElement {
    private double amount;

    public Income(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(TaxVisitors visitor) {
        visitor.visit(this);
    }
}

class Expense implements TaxElement {
    private double amount;

    public Expense(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(TaxVisitors visitor) {
        visitor.visit(this);
    }
}

interface TaxVisitors {
    void visit(Income income);
    void visit(Expense expense);
}

class TaxCalculationVisitor implements TaxVisitors {
    private double totalTax;

    @Override
    public void visit(Income income) {
        totalTax += income.getAmount() * 0.2; // Example: 20% tax on income
    }

    @Override
    public void visit(Expense expense) {
        totalTax -= expense.getAmount() * 0.1; // Example: 10% deduction on expenses
    }

    public double getTotalTax() {
        return totalTax;
    }
}

// Client Code
public class TaxFilingSystemApp {
    public static void main(String[] args) {
        // Template Method Pattern - Filing Taxes
        TaxFiling individualFiling = new IndividualTaxFiling();
        TaxFiling corporateFiling = new CorporateTaxFiling();

        System.out.println("Filing Individual Tax:");
        individualFiling.fileTax();

        System.out.println("\nFiling Corporate Tax:");
        corporateFiling.fileTax();

        // Visitor Pattern - Calculating Tax
        List<TaxElement> elements = new ArrayList<>();
        elements.add(new Income(50000));
        elements.add(new Expense(10000));

        TaxCalculationVisitor taxCalculator = new TaxCalculationVisitor();
        for (TaxElement element : elements) {
            element.accept(taxCalculator);
        }

        System.out.println("\nTotal Tax Calculated: " + taxCalculator.getTotalTax());
    }
}

/**
Explanation:
1. `TaxFiling` is the abstract class for the Template Method pattern that defines the steps for filing taxes.
2. `IndividualTaxFiling` and `CorporateTaxFiling` are concrete implementations of `TaxFiling` that specify the process for different types of taxpayers.
3. `TaxElement` is the element interface for the Visitor pattern that defines the `accept()` method for accepting visitors.
4. `Income` and `Expense` are concrete elements that represent different components of tax calculation and accept visitors.
5. `TaxVisitor` is the visitor interface that defines the `visit()` methods for different tax elements.
6. `TaxCalculationVisitor` is the concrete visitor that implements tax calculation logic for income and expenses.
7. `TaxFilingSystemApp` is the client code that demonstrates using both the Template Method pattern for handling different tax filing methods and the Visitor pattern for tax calculations.
*/
