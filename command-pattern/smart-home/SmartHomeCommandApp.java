import java.util.ArrayList;
import java.util.List;

// Command Interface
interface Command {
    void execute();
}

// Receiver Classes
class Light {
    public void turnOn() {
        System.out.println("Light is turned on.");
    }

    public void turnOff() {
        System.out.println("Light is turned off.");
    }
}

class Thermostats {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat set to " + temperature + " degrees.");
    }
}

// Concrete Command Classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class SetTemperatureCommand implements Command {
    private Thermostats thermostat;
    private int temperature;

    public SetTemperatureCommand(Thermostats thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }
}

// Invoker Class
class SmartHomeApp {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }
}

// Client Code
public class SmartHomeCommandApp {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Thermostats homeThermostat = new Thermostats();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command setTemperature = new SetTemperatureCommand(homeThermostat, 22);

        SmartHomeApp smartHomeApp = new SmartHomeApp();

        smartHomeApp.executeCommand(lightOn);
        smartHomeApp.executeCommand(setTemperature);
        smartHomeApp.executeCommand(lightOff);
    }
}

/**
Explanation:
1. `Command` is the command interface that defines the `execute()` method for all commands.
2. `Light` and `Thermostat` are receiver classes that perform the actual actions.
3. `LightOnCommand`, `LightOffCommand`, and `SetTemperatureCommand` are concrete command classes that implement the `Command` interface and perform specific actions on the receivers.
4. `SmartHomeApp` is the invoker class that executes commands and keeps a history of executed commands.
5. `SmartHomeCommandApp` is the client that creates commands and uses the `SmartHomeApp` to execute them.
*/

