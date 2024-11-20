import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface FlightControlMediator {
    void requestLanding(Airplane airplane);
    void notifyAvailability();
}

// Concrete Mediator Class
class AirportControl implements FlightControlMediator {
    private boolean runwayAvailable;
    private List<Airplane> waitingQueue;

    public AirportControl() {
        this.runwayAvailable = true;
        this.waitingQueue = new ArrayList<>();
    }

    @Override
    public void requestLanding(Airplane airplane) {
        if (runwayAvailable) {
            System.out.println("Runway is available. " + airplane.getName() + " is landing.");
            runwayAvailable = false;
        } else {
            System.out.println("Runway is not available. " + airplane.getName() + " is waiting.");
            waitingQueue.add(airplane);
        }
    }

    @Override
    public void notifyAvailability() {
        System.out.println("Runway is now available.");
        if (!waitingQueue.isEmpty()) {
            Airplane nextAirplane = waitingQueue.remove(0);
            System.out.println(nextAirplane.getName() + " is cleared to land.");
            nextAirplane.land();
        } else {
            runwayAvailable = true;
        }
    }
}

// Colleague Class
abstract class Airplane {
    protected FlightControlMediator mediator;
    protected String name;

    public Airplane(FlightControlMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void requestLanding();
    public abstract void land();
}

// Concrete Colleague Class
class ConcreteAirplane extends Airplane {
    public ConcreteAirplane(FlightControlMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void requestLanding() {
        System.out.println(name + " requesting to land.");
        mediator.requestLanding(this);
    }

    @Override
    public void land() {
        System.out.println(name + " is landing now.");
        mediator.notifyAvailability();
    }
}

// Client Code
public class FlightControlApp {
    public static void main(String[] args) {
        FlightControlMediator control = new AirportControl();

        Airplane airplane1 = new ConcreteAirplane(control, "Flight A1");
        Airplane airplane2 = new ConcreteAirplane(control, "Flight B2");
        Airplane airplane3 = new ConcreteAirplane(control, "Flight C3");

        airplane1.requestLanding();
        airplane2.requestLanding();
        airplane1.land();
        airplane3.requestLanding();
        airplane2.land();
    }
}

/**
Explanation:
1. `FlightControlMediator` is the mediator interface that defines methods for handling landing requests and notifying availability.
2. `AirportControl` is the concrete mediator that coordinates the landing and takeoff of airplanes, managing runway availability.
3. `Airplane` is the abstract colleague class that represents an airplane interacting with the mediator.
4. `ConcreteAirplane` is the concrete colleague that requests landing and interacts with the mediator for runway management.
5. `FlightControlApp` is the client that creates airplanes and allows them to request landing and takeoff through the mediator.
*/
