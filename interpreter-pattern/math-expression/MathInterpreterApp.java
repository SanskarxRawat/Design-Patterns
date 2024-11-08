/**
 * Interpreter Pattern: Build a "Math Expression Interpreter" that evaluates simple math expressions like "5 + 3 * 2".
 */


// Abstract Expression Interface
interface Expression {
    int interpret();
}

// Terminal Expression for Numbers
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// Non-Terminal Expression for Addition
class AdditionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AdditionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

// Non-Terminal Expression for Multiplication
class MultiplicationExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public MultiplicationExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() * rightExpression.interpret();
    }
}

// Client Code
public class MathInterpreterApp {
    public static void main(String[] args) {
        // Representing the expression: 5 + 3 * 2
        Expression number5 = new NumberExpression(5);
        Expression number3 = new NumberExpression(3);
        Expression number2 = new NumberExpression(2);

        // 3 * 2
        Expression multiplication = new MultiplicationExpression(number3, number2);

        // 5 + (3 * 2)
        Expression addition = new AdditionExpression(number5, multiplication);

        System.out.println("Result of 5 + 3 * 2: " + addition.interpret());
    }
}

/**
Explanation:
1. `Expression` is the abstract expression interface that defines the `interpret()` method for evaluating expressions.
2. `NumberExpression` is a terminal expression that represents individual numbers.
3. `AdditionExpression` and `MultiplicationExpression` are non-terminal expressions that represent addition and multiplication operations, respectively.
4. `MathInterpreterApp` is the client that creates and evaluates a simple math expression using the interpreter pattern.
*/
