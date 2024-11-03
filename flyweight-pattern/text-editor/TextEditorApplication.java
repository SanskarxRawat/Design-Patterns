import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Pattern: Implement a "Text Editor" that uses flyweight characters to save memory while handling large documents.
 */
// Flyweight Interface
interface AlphabetCharacter {
    void display(int fontSize);
}

// Concrete Flyweight Class
class ConcreteAlphabetCharacter implements AlphabetCharacter {
    private char symbol;

    public ConcreteAlphabetCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int fontSize) {
        System.out.println("Character: " + symbol + " with font size: " + fontSize);
    }
}

// Flyweight Factory
class CharacterFactory {
    private Map<Character, ConcreteAlphabetCharacter> characterPool = new HashMap<>();

    public AlphabetCharacter getCharacter(char symbol) {
        ConcreteAlphabetCharacter character = characterPool.get(symbol);
        if (character == null) {
            character = new ConcreteAlphabetCharacter(symbol);
            characterPool.put(symbol, character);
        }
        return character;
    }
}

// Client Code
public class TextEditorApplication {
    public static void main(String[] args) {
        CharacterFactory characterFactory = new CharacterFactory();

        String document = "Hello, World!";
        int[] fontSizes = {12, 14, 16, 12, 14, 16, 12, 14, 16, 12, 14, 16, 12};

        for (int i = 0; i < document.length(); i++) {
            AlphabetCharacter character = characterFactory.getCharacter(document.charAt(i));
            character.display(fontSizes[i % fontSizes.length]);
        }
    }
}

/**
Explanation:
1. `Character` is the flyweight interface that defines the method `display()` to show the character with a given font size.
2. `ConcreteCharacter` is the concrete flyweight class that represents an individual character.
3. `CharacterFactory` is the flyweight factory that manages the shared instances of `ConcreteCharacter` to minimize memory usage.
4. `TextEditorApp` is the client that uses the `CharacterFactory` to create and display characters efficiently, demonstrating how the Flyweight pattern can save memory when handling large documents.
*/
