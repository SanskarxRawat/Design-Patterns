// Strategy Interface for Fare Calculation
interface FareCalculationStrategy {
    double calculateFare(double baseFare);
}

// Concrete Strategy Classes
class EconomyFareCalculation implements FareCalculationStrategy {
    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 1.0; // No additional cost for economy
    }
}

class BusinessFareCalculation implements FareCalculationStrategy {
    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 1.5; // 50% extra for business class
    }
}

class FirstClassFareCalculation implements FareCalculationStrategy {
    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 2.0; // 100% extra for first class
    }
}

// Product Interface for Booking
interface Booking {
    void book(String passengerName);
}

// Concrete Booking Types
class EconomyBooking implements Booking {
    @Override
    public void book(String passengerName) {
        System.out.println("Economy class booked for: " + passengerName);
    }
}

class BusinessBooking implements Booking {
    @Override
    public void book(String passengerName) {
        System.out.println("Business class booked for: " + passengerName);
    }
}

class FirstClassBooking implements Booking {
    @Override
    public void book(String passengerName) {
        System.out.println("First class booked for: " + passengerName);
    }
}

// Factory Class for Booking Types
class BookingFactory {
    public static Booking createBooking(String type) {
        switch (type.toLowerCase()) {
            case "economy":
                return new EconomyBooking();
            case "business":
                return new BusinessBooking();
            case "first":
                return new FirstClassBooking();
            default:
                throw new IllegalArgumentException("Unknown booking type: " + type);
        }
    }
}

// Context Class for Booking System
class FlightBooking {
    private FareCalculationStrategy fareCalculationStrategy;
    private Booking booking;
    private double baseFare;

    public FlightBooking(double baseFare) {
        this.baseFare = baseFare;
    }

    public void setFareCalculationStrategy(FareCalculationStrategy fareCalculationStrategy) {
        this.fareCalculationStrategy = fareCalculationStrategy;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void bookFlight(String passengerName) {
        if (fareCalculationStrategy == null || booking == null) {
            System.out.println("Booking information incomplete.");
            return;
        }
        double fare = fareCalculationStrategy.calculateFare(baseFare);
        System.out.println("Total Fare for " + passengerName + ": $" + fare);
        booking.book(passengerName);
    }
}

// Client Code
public class BookingSystemApp {
    public static void main(String[] args) {
        double baseFare = 100.0;
        FlightBooking flightBooking = new FlightBooking(baseFare);

        // Booking for Economy Class
        flightBooking.setFareCalculationStrategy(new EconomyFareCalculation());
        flightBooking.setBooking(BookingFactory.createBooking("economy"));
        flightBooking.bookFlight("Alice");

        // Booking for Business Class
        flightBooking.setFareCalculationStrategy(new BusinessFareCalculation());
        flightBooking.setBooking(BookingFactory.createBooking("business"));
        flightBooking.bookFlight("Bob");

        // Booking for First Class
        flightBooking.setFareCalculationStrategy(new FirstClassFareCalculation());
        flightBooking.setBooking(BookingFactory.createBooking("first"));
        flightBooking.bookFlight("Charlie");
    }
}

/**
Explanation:
1. `FareCalculationStrategy` is the strategy interface that defines the method `calculateFare()` for calculating fare.
2. `EconomyFareCalculation`, `BusinessFareCalculation`, and `FirstClassFareCalculation` are concrete strategy classes that implement different fare calculation algorithms.
3. `Booking` is the product interface that defines the method `book()` for different booking types.
4. `EconomyBooking`, `BusinessBooking`, and `FirstClassBooking` are concrete booking types.
5. `BookingFactory` is the factory class that creates instances of booking types based on the input.
6. `FlightBooking` is the context class that maintains references to both fare calculation strategies and booking types.
7. `BookingSystemApp` is the client that demonstrates booking different flight classes using both Strategy and Factory patterns.
*/
