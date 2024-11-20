// State Pattern - Traffic Light Phases

// Strategy Pattern - Choosing Best Route
interface RouteStrategy {
    void findRoute(String startPoint, String endPoint);
}

class ShortestRouteStrategy implements RouteStrategy {
    @Override
    public void findRoute(String startPoint, String endPoint) {
        System.out.println("Finding the shortest route from " + startPoint + " to " + endPoint);
    }
}

class ScenicRouteStrategy implements RouteStrategy {
    @Override
    public void findRoute(String startPoint, String endPoint) {
        System.out.println("Finding the scenic route from " + startPoint + " to " + endPoint);
    }
}

class TrafficAvoidanceStrategy implements RouteStrategy {
    @Override
    public void findRoute(String startPoint, String endPoint) {
        System.out.println("Finding the route with the least traffic from " + startPoint + " to " + endPoint);
    }
}

class NavigationContext {
    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void navigate(String startPoint, String endPoint) {
        if (routeStrategy != null) {
            routeStrategy.findRoute(startPoint, endPoint);
        } else {
            System.out.println("No route strategy set.");
        }
    }
}

// Client Code
public class TrafficNavigationApp {
    public static void main(String[] args) {
        // State Pattern - Managing Traffic Light Phases
        TrafficLightContext trafficLight = new TrafficLightContext();
        System.out.println("Traffic Light Phases:");
        trafficLight.changeLight();
        trafficLight.changeLight();
        trafficLight.changeLight();
        trafficLight.changeLight();

        // Strategy Pattern - Choosing Best Route
        NavigationContext navigation = new NavigationContext();
        System.out.println("\nChoosing Best Route:");

        navigation.setRouteStrategy(new ShortestRouteStrategy());
        navigation.navigate("Point A", "Point B");

        navigation.setRouteStrategy(new ScenicRouteStrategy());
        navigation.navigate("Point A", "Point B");

        navigation.setRouteStrategy(new TrafficAvoidanceStrategy());
        navigation.navigate("Point A", "Point B");
    }
}

/**
Explanation:
1. `TrafficLightState` is the state interface that defines the method `handleState()` for transitioning between traffic light phases.
2. `RedLightState`, `GreenLightState`, and `YellowLightState` are concrete state classes that implement the behavior for each traffic light phase.
3. `TrafficLightContext` maintains the current state of the traffic light and handles state transitions.
4. `RouteStrategy` is the strategy interface that defines the method `findRoute()` for finding the best route.
5. `ShortestRouteStrategy`, `ScenicRouteStrategy`, and `TrafficAvoidanceStrategy` are concrete strategy classes that implement different route-finding algorithms.
6. `NavigationContext` maintains a reference to a `RouteStrategy` and uses it to navigate between points.
7. `TrafficNavigationApp` is the client code that demonstrates using both the State pattern for managing traffic light phases and the Strategy pattern for choosing the best route.
*/
