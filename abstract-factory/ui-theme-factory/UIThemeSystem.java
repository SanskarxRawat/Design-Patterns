import java.util.Scanner;

/**
 * Abstract Factory: Create a "UI Theme Factory" that produces different UI elements (buttons, text fields, dropdowns) based on the selected theme (light or dark mode).
 */
// Abstract Product Interfaces
interface Button {
    void render();
}

interface TextField {
    void render();
}

interface Dropdown {
    void render();
}

// Concrete Product Classes for Light Theme
class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering light theme button...");
    }
}

class LightTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering light theme text field...");
    }
}

class LightDropdown implements Dropdown {
    @Override
    public void render() {
        System.out.println("Rendering light theme dropdown...");
    }
}

// Concrete Product Classes for Dark Theme
class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering dark theme button...");
    }
}

class DarkTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering dark theme text field...");
    }
}

class DarkDropdown implements Dropdown {
    @Override
    public void render() {
        System.out.println("Rendering dark theme dropdown...");
    }
}

// Abstract Factory Interface
interface UIThemeFactory {
    Button createButton();
    TextField createTextField();
    Dropdown createDropdown();
}

// Concrete Factory Classes
class LightThemeFactory implements UIThemeFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public TextField createTextField() {
        return new LightTextField();
    }

    @Override
    public Dropdown createDropdown() {
        return new LightDropdown();
    }
}

class DarkThemeFactory implements UIThemeFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }

    @Override
    public Dropdown createDropdown() {
        return new DarkDropdown();
    }
}

// Client Code
public class UIThemeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the theme (light or dark): ");
        String theme = scanner.nextLine();

        UIThemeFactory factory;
        if (theme.equalsIgnoreCase("light")) {
            factory = new LightThemeFactory();
        } else if (theme.equalsIgnoreCase("dark")) {
            factory = new DarkThemeFactory();
        } else {
            throw new IllegalArgumentException("Unknown theme: " + theme);
        }

        Button button = factory.createButton();
        TextField textField = factory.createTextField();
        Dropdown dropdown = factory.createDropdown();

        button.render();
        textField.render();
        dropdown.render();
    }
}

/**
Explanation:
1. The `Button`, `TextField`, and `Dropdown` interfaces define common UI elements.
2. `LightButton`, `LightTextField`, `LightDropdown`, `DarkButton`, `DarkTextField`, and `DarkDropdown` are concrete implementations for the light and dark themes.
3. `UIThemeFactory` is the abstract factory interface that declares methods for creating UI elements.
4. `LightThemeFactory` and `DarkThemeFactory` are concrete factories that produce light and dark-themed UI elements.
5. The `UIThemeSystem` class is the client that interacts with the user to select a theme and generates the appropriate UI elements using the factory.
*/
