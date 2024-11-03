/**
 * Builder Pattern: Design a "Vacation Planner" where users can choose various options (hotel, car rental, flight) to build their vacation package.
 */
// Product Class
class VacationPackage {
    private String hotel;
    private String carRental;
    private String flight;

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setCarRental(String carRental) {
        this.carRental = carRental;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "VacationPackage{" +
                "hotel='" + hotel + '\'' +
                ", carRental='" + carRental + '\'' +
                ", flight='" + flight + '\'' +
                '}';
    }
}

// Builder Interface
interface VacationPackageBuilder {
    void addHotel(String hotel);
    void addCarRental(String carRental);
    void addFlight(String flight);
    VacationPackage build();
}

// Concrete Builder
class ConcreteVacationPackageBuilder implements VacationPackageBuilder {
    private VacationPackage vacationPackage;

    public ConcreteVacationPackageBuilder() {
        this.vacationPackage = new VacationPackage();
    }

    @Override
    public void addHotel(String hotel) {
        vacationPackage.setHotel(hotel);
    }

    @Override
    public void addCarRental(String carRental) {
        vacationPackage.setCarRental(carRental);
    }

    @Override
    public void addFlight(String flight) {
        vacationPackage.setFlight(flight);
    }

    @Override
    public VacationPackage build() {
        return vacationPackage;
    }
}

// Director Class
class VacationPlanner {
    private VacationPackageBuilder builder;

    public VacationPlanner(VacationPackageBuilder builder) {
        this.builder = builder;
    }

    public VacationPackage createVacationPackage(String hotel, String carRental, String flight) {
        builder.addHotel(hotel);
        builder.addCarRental(carRental);
        builder.addFlight(flight);
        return builder.build();
    }
}

// Client Code
public class VacationPlannerApp {
    public static void main(String[] args) {
        VacationPackageBuilder builder = new ConcreteVacationPackageBuilder();
        VacationPlanner planner = new VacationPlanner(builder);

        VacationPackage vacationPackage = planner.createVacationPackage("Grand Hotel", "Luxury Car", "First Class Flight");
        System.out.println(vacationPackage);
    }
}

/**
Explanation:
1. `VacationPackage` is the product class that represents the vacation package.
2. `VacationPackageBuilder` is the builder interface that defines methods to add different components to the package.
3. `ConcreteVacationPackageBuilder` is the concrete implementation of the builder that constructs the `VacationPackage`.
4. `VacationPlanner` acts as the director, controlling the construction process of the vacation package.
5. `VacationPlannerApp` is the client that uses the builder pattern to create a vacation package.
*/
