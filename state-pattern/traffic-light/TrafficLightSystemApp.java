// State Interface
interface TrafficLightState {
    void handleState(TrafficLightContext context);
}

// Concrete State Classes
class RedLightState implements TrafficLightState {
    @Override
    public void handleState(TrafficLightContext context) {
        System.out.println("Red Light - Stop");
        context.setState(new GreenLightState());
    }
}

class GreenLightState implements TrafficLightState {
    @Override
    public void handleState(TrafficLightContext context) {
        System.out.println("Green Light - Go");
        context.setState(new YellowLightState());
    }
}

class YellowLightState implements TrafficLightState {
    @Override
    public void handleState(TrafficLightContext context) {
        System.out.println("Yellow Light - Slow Down");
        context.setState(new RedLightState());
    }
}

// Context Class
class TrafficLightContext {
    private TrafficLightState state;

    public TrafficLightContext() {
        // Initial state is Red Light
        state = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void changeLight() {
        state.handleState(this);
    }
}

// Client Code
public class TrafficLightSystemApp {
    public static void main(String[] args) throws InterruptedException {
        TrafficLightContext trafficLight = new TrafficLightContext();

        // Simulate the traffic light changing state based on a timer
        for (int i = 0; i < 6; i++) {
            trafficLight.changeLight();
            Thread.sleep(2000); // Wait for 2 seconds before changing to the next state
        }
    }
}

/**
Explanation:
1. `TrafficLightState` is the state interface that defines the method `handleState()` for transitioning between states.
2. `RedLightState`, `GreenLightState`, and `YellowLightState` are concrete state classes that implement the behavior for each traffic light state.
3. `TrafficLightContext` is the context class that maintains a reference to the current state and delegates state transitions.
4. `TrafficLightSystemApp` is the client that simulates the traffic light system changing state based on a timer.
*/
