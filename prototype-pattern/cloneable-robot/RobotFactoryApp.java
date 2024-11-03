import java.util.HashMap;
import java.util.Map;

// Prototype Interface
abstract class Robot implements Cloneable {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Robot clone() {
        try {
            return (Robot) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract void showAbilities();
}

// Concrete Prototype Classes
class CleaningRobot extends Robot {
    public CleaningRobot() {
        setType("Cleaning Robot");
    }

    @Override
    void showAbilities() {
        System.out.println("I can clean floors and surfaces.");
    }
}

class CookingRobot extends Robot {
    public CookingRobot() {
        setType("Cooking Robot");
    }

    @Override
    void showAbilities() {
        System.out.println("I can cook various dishes.");
    }
}

// Prototype Registry
class RobotRegistry {
    private Map<String, Robot> robotMap = new HashMap<>();

    public RobotRegistry() {
        loadRobots();
    }

    public Robot getRobot(String robotId) {
        Robot cachedRobot = robotMap.get(robotId);
        return (cachedRobot != null) ? cachedRobot.clone() : null;
    }

    private void loadRobots() {
        CleaningRobot cleaningRobot = new CleaningRobot();
        cleaningRobot.setId("1");
        robotMap.put(cleaningRobot.getId(), cleaningRobot);

        CookingRobot cookingRobot = new CookingRobot();
        cookingRobot.setId("2");
        robotMap.put(cookingRobot.getId(), cookingRobot);
    }
}

// Client Code
public class RobotFactoryApp {
    public static void main(String[] args) {
        RobotRegistry robotRegistry = new RobotRegistry();

        Robot clonedCleaningRobot = robotRegistry.getRobot("1");
        System.out.println("Cloned Robot: " + clonedCleaningRobot.getType());
        clonedCleaningRobot.showAbilities();

        Robot clonedCookingRobot = robotRegistry.getRobot("2");
        System.out.println("Cloned Robot: " + clonedCookingRobot.getType());
        clonedCookingRobot.showAbilities();

        // Customizing cloned robots
        Robot customizedRobot = clonedCookingRobot.clone();
        customizedRobot.setId("3");
        System.out.println("Customized Robot: " + customizedRobot.getType());
        customizedRobot.showAbilities();
    }
}

/**
Explanation:
1. `Robot` is the prototype interface that defines a `clone()` method for creating copies of robots.
2. `CleaningRobot` and `CookingRobot` are concrete classes that extend `Robot` and implement the `showAbilities()` method.
3. `RobotRegistry` acts as a registry for storing and retrieving robot prototypes. It provides a `getRobot()` method that returns a cloned robot.
4. `RobotFactoryApp` is the client that uses the `RobotRegistry` to create and customize robots.
*/
