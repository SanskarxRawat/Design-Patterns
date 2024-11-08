/**
 * Interpreter Pattern: Implement a "SQL Query Interpreter" that can parse and execute basic SQL commands.
 */


// Abstract Expression Interface
interface SQLExpression {
    void interpret(Context context);
}

// Context Class
class Context {
    private String query;

    public Context(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void executeQuery(String operation, String table) {
        System.out.println("Executing: " + operation + " on table: " + table);
    }
}

// Terminal Expression for Select
class SelectExpression implements SQLExpression {
    private String table;

    public SelectExpression(String table) {
        this.table = table;
    }

    @Override
    public void interpret(Context context) {
        context.executeQuery("SELECT *", table);
    }
}

// Terminal Expression for Insert
class InsertExpression implements SQLExpression {
    private String table;

    public InsertExpression(String table) {
        this.table = table;
    }

    @Override
    public void interpret(Context context) {
        context.executeQuery("INSERT INTO", table);
    }
}

// Terminal Expression for Delete
class DeleteExpression implements SQLExpression {
    private String table;

    public DeleteExpression(String table) {
        this.table = table;
    }

    @Override
    public void interpret(Context context) {
        context.executeQuery("DELETE FROM", table);
    }
}

// Client Code
public class SQLInterpreterApp {
    public static void main(String[] args) {
        String query1 = "SELECT * FROM users";
        String query2 = "INSERT INTO orders";
        String query3 = "DELETE FROM products";

        Context context1 = new Context(query1);
        SQLExpression selectExpression = new SelectExpression("users");
        selectExpression.interpret(context1);

        Context context2 = new Context(query2);
        SQLExpression insertExpression = new InsertExpression("orders");
        insertExpression.interpret(context2);

        Context context3 = new Context(query3);
        SQLExpression deleteExpression = new DeleteExpression("products");
        deleteExpression.interpret(context3);
    }
}

/**
Explanation:
1. `SQLExpression` is the abstract expression interface that defines the `interpret()` method for executing SQL commands.
2. `Context` class represents the context in which the SQL commands are interpreted and executed.
3. `SelectExpression`, `InsertExpression`, and `DeleteExpression` are terminal expressions that represent specific SQL commands like SELECT, INSERT, and DELETE.
4. `SQLInterpreterApp` is the client that creates the context and different SQL expressions, and executes them using the interpreter pattern.
*/
