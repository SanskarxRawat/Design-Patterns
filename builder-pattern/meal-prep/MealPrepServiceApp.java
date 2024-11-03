/**
 * Builder Pattern: Create a "Meal Prep Service" where customers can build their own meal, choosing from different bases, proteins, veggies, and sauces.
 */
// Product Class
class Meal {
    private String base;
    private String protein;
    private String veggies;
    private String sauce;

    public void setBase(String base) {
        this.base = base;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setVeggies(String veggies) {
        this.veggies = veggies;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "base='" + base + '\'' +
                ", protein='" + protein + '\'' +
                ", veggies='" + veggies + '\'' +
                ", sauce='" + sauce + '\'' +
                '}';
    }
}

// Builder Interface
interface MealBuilder {
    void addBase(String base);
    void addProtein(String protein);
    void addVeggies(String veggies);
    void addSauce(String sauce);
    Meal build();
}

// Concrete Builder
class ConcreteMealBuilder implements MealBuilder {
    private Meal meal;

    public ConcreteMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public void addBase(String base) {
        meal.setBase(base);
    }

    @Override
    public void addProtein(String protein) {
        meal.setProtein(protein);
    }

    @Override
    public void addVeggies(String veggies) {
        meal.setVeggies(veggies);
    }

    @Override
    public void addSauce(String sauce) {
        meal.setSauce(sauce);
    }

    @Override
    public Meal build() {
        return meal;
    }
}

// Director Class
class MealPrepService {
    private MealBuilder builder;

    public MealPrepService(MealBuilder builder) {
        this.builder = builder;
    }

    public Meal createMeal(String base, String protein, String veggies, String sauce) {
        builder.addBase(base);
        builder.addProtein(protein);
        builder.addVeggies(veggies);
        builder.addSauce(sauce);
        return builder.build();
    }
}

// Client Code
public class MealPrepServiceApp {
    public static void main(String[] args) {
        MealBuilder builder = new ConcreteMealBuilder();
        MealPrepService mealPrepService = new MealPrepService(builder);

        Meal meal = mealPrepService.createMeal("Brown Rice", "Chicken", "Broccoli", "Teriyaki Sauce");
        System.out.println(meal);
    }
}

/**
Explanation:
1. `Meal` is the product class that represents the meal being prepared.
2. `MealBuilder` is the builder interface that defines methods to add different components to the meal.
3. `ConcreteMealBuilder` is the concrete implementation of the builder that constructs the `Meal`.
4. `MealPrepService` acts as the director, controlling the construction process of the meal.
5. `MealPrepServiceApp` is the client that uses the builder pattern to create a meal.
*/
