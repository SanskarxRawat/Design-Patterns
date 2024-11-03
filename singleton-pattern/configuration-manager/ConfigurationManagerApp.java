/**
 * Singleton Pattern: Implement a "Configuration Manager" that ensures only one instance of the config settings is created and accessible across the application.
 */
// Singleton Class
class ConfigurationManager {
    // Static instance of ConfigurationManager
    private static ConfigurationManager instance;

    // Private constructor to prevent instantiation
    private ConfigurationManager() {
        // Load configuration settings here, e.g., from a file or database
        System.out.println("Loading configuration settings...");
    }

    // Public method to provide access to the instance
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    // Example configuration methods
    public String getConfigValue(String key) {
        // Return a value for the given key (for simplicity, returning a static value)
        return "Value for " + key;
    }
}

// Client Code
public class ConfigurationManagerApp {
    public static void main(String[] args) {
        ConfigurationManager configManager1 = ConfigurationManager.getInstance();
        ConfigurationManager configManager2 = ConfigurationManager.getInstance();

        System.out.println("Config value: " + configManager1.getConfigValue("AppName"));

        // Verify that both instances are the same
        if (configManager1 == configManager2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Instances are different.");
        }
    }
}

/**
Explanation:
1. `ConfigurationManager` is a singleton class that ensures only one instance of the configuration settings is created.
2. The `getInstance()` method provides global access to the instance. It uses double-checked locking to ensure thread safety and lazy initialization.
3. The constructor is private to prevent instantiation from other classes.
4. `ConfigurationManagerApp` is the client that accesses the configuration settings using the singleton instance.
*/

