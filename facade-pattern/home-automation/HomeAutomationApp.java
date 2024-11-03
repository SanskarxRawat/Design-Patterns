/**
 * Facade Pattern: Create a "Home Automation Facade" that controls lights, thermostat, and security system from a single interface.
 */
// Subsystem Class: Lights
class Lights {
    public void turnOn() {
        System.out.println("Lights are turned on.");
    }

    public void turnOff() {
        System.out.println("Lights are turned off.");
    }
}

// Subsystem Class: Thermostat
class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat set to " + temperature + " degrees.");
    }
}

// Subsystem Class: Security System
class SecuritySystem {
    public void armSystem() {
        System.out.println("Security system armed.");
    }

    public void disarmSystem() {
        System.out.println("Security system disarmed.");
    }
}

// Facade Class
class HomeAutomationFacade {
    private Lights lights;
    private Thermostat thermostat;
    private SecuritySystem securitySystem;

    public HomeAutomationFacade() {
        this.lights = new Lights();
        this.thermostat = new Thermostat();
        this.securitySystem = new SecuritySystem();
    }

    public void leaveHome() {
        lights.turnOff();
        thermostat.setTemperature(18);
        securitySystem.armSystem();
        System.out.println("Home is set for leaving.");
    }

    public void arriveHome() {
        lights.turnOn();
        thermostat.setTemperature(22);
        securitySystem.disarmSystem();
        System.out.println("Welcome home!");
    }
}

// Client Code
public class HomeAutomationApp {
    public static void main(String[] args) {
        HomeAutomationFacade homeFacade = new HomeAutomationFacade();
        homeFacade.leaveHome();
        System.out.println();
        homeFacade.arriveHome();
    }
}

/**
Explanation:
1. `Lights`, `Thermostat`, and `SecuritySystem` are subsystem classes that handle different parts of home automation.
2. `HomeAutomationFacade` is the facade class that provides a simplified interface to the client by combining the operations of lights, thermostat, and security system.
3. `HomeAutomationApp` is the client that interacts with the facade to control the home automation system in a simplified way.
*/
