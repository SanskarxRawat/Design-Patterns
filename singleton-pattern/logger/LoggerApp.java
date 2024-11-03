/**
 * Singleton Pattern: Develop a "Logger" that ensures a single logging instance for the entire application.
 */
// Singleton Logger Class
class Logger {
    // Static instance of Logger
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
        // Initialization for the logger (e.g., setting up log files)
        System.out.println("Logger initialized...");
    }

    // Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        // For simplicity, printing to console
        System.out.println("Log: " + message);
    }
}

// Client Code
public class LoggerApp {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Verify that both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both logger instances are the same.");
        } else {
            System.out.println("Logger instances are different.");
        }
    }
}

/**
Explanation:
1. `Logger` is a singleton class that ensures only one instance of the logger is created.
2. The `getInstance()` method provides global access to the logger instance. It uses double-checked locking to ensure thread safety and lazy initialization.
3. The constructor is private to prevent instantiation from other classes.
4. The `log()` method allows logging messages, which in this case are printed to the console.
5. `LoggerApp` is the client that accesses the logger instance and logs messages.
*/
