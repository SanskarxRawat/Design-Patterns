// Template Method Pattern - Recipe Builder
abstract class Recipe {
    // Template Method
    public final void prepareRecipe() {
        prepareIngredients();
        cook();
        serve();
    }

    protected abstract void prepareIngredients();
    protected abstract void cook();

    protected void serve() {
        System.out.println("Serving the dish.");
    }
}

// Concrete Class for Pasta Recipe
class PastaRecipe extends Recipe {
    @Override
    protected void prepareIngredients() {
        System.out.println("Preparing pasta, tomatoes, garlic, and olive oil.");
    }

    @Override
    protected void cook() {
        System.out.println("Cooking pasta and making the sauce.");
    }
}

// Concrete Class for Salad Recipe
class SaladRecipe extends Recipe {
    @Override
    protected void prepareIngredients() {
        System.out.println("Preparing lettuce, cucumber, tomatoes, and dressing.");
    }

    @Override
    protected void cook() {
        System.out.println("Mixing all the ingredients together.");
    }
}

// Factory Pattern - Ingredient Selection
interface Ingredient {
    void select();
}

class Tomato implements Ingredient {
    @Override
    public void select() {
        System.out.println("Selecting fresh tomatoes.");
    }
}

class Lettuce implements Ingredient {
    @Override
    public void select() {
        System.out.println("Selecting fresh lettuce.");
    }
}

class IngredientFactory {
    public static Ingredient getIngredient(String type) {
        switch (type.toLowerCase()) {
            case "tomato":
                return new Tomato();
            case "lettuce":
                return new Lettuce();
            default:
                throw new IllegalArgumentException("Unknown ingredient type: " + type);
        }
    }
}

// Client Code
public class RecipeBuilderApp {
    public static void main(String[] args) {
        // Template Method Pattern - Preparing Recipes
        Recipe pastaRecipe = new PastaRecipe();
        Recipe saladRecipe = new SaladRecipe();

        System.out.println("Preparing Pasta Recipe:");
        pastaRecipe.prepareRecipe();

        System.out.println("\nPreparing Salad Recipe:");
        saladRecipe.prepareRecipe();

        // Factory Pattern - Selecting Ingredients
        System.out.println("\nSelecting Ingredients:");
        Ingredient tomato = IngredientFactory.getIngredient("tomato");
        tomato.select();

        Ingredient lettuce = IngredientFactory.getIngredient("lettuce");
        lettuce.select();
    }
}

/**
Explanation:
1. `Recipe` is the abstract class that defines the template method `prepareRecipe()`, which outlines the steps for preparing a recipe.
2. `PastaRecipe` and `SaladRecipe` are concrete classes that implement the specific steps for preparing ingredients and cooking.
3. `Ingredient` is the product interface for the Factory pattern that defines the method `select()` for selecting ingredients.
4. `Tomato` and `Lettuce` are concrete implementations of different ingredients.
5. `IngredientFactory` is the factory class that creates instances of ingredients based on the input.
6. `RecipeBuilderApp` is the client code that demonstrates the usage of both the Template Method and Factory patterns for preparing recipes and selecting ingredients.
*/
