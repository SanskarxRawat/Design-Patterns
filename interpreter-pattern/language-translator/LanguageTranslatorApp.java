// Interpreter Pattern - Language Translator
interface Expressions {
    String interpret(String context);
}

class EnglishToSpanish implements Expressions {
    @Override
    public String interpret(String context) {
        switch (context.toLowerCase()) {
            case "hello":
                return "hola";
            case "goodbye":
                return "adios";
            default:
                return "Translation not available";
        }
    }
}

class EnglishToFrench implements Expressions {
    @Override
    public String interpret(String context) {
        switch (context.toLowerCase()) {
            case "hello":
                return "bonjour";
            case "goodbye":
                return "au revoir";
            default:
                return "Translation not available";
        }
    }
}

// Adapter Pattern - Integrating with Various Language Databases
interface LanguageDatabase {
    String getTranslation(String word);
}

class SpanishDatabase {
    public String buscarTraduccion(String palabra) {
        if (palabra.equalsIgnoreCase("hello")) {
            return "hola";
        } else if (palabra.equalsIgnoreCase("goodbye")) {
            return "adios";
        }
        return "No disponible";
    }
}

class SpanishDatabaseAdapter implements LanguageDatabase {
    private SpanishDatabase spanishDatabase;

    public SpanishDatabaseAdapter(SpanishDatabase spanishDatabase) {
        this.spanishDatabase = spanishDatabase;
    }

    @Override
    public String getTranslation(String word) {
        return spanishDatabase.buscarTraduccion(word);
    }
}

class FrenchDatabase {
    public String chercherTraduction(String mot) {
        if (mot.equalsIgnoreCase("hello")) {
            return "bonjour";
        } else if (mot.equalsIgnoreCase("goodbye")) {
            return "au revoir";
        }
        return "Non disponible";
    }
}

class FrenchDatabaseAdapter implements LanguageDatabase {
    private FrenchDatabase frenchDatabase;

    public FrenchDatabaseAdapter(FrenchDatabase frenchDatabase) {
        this.frenchDatabase = frenchDatabase;
    }

    @Override
    public String getTranslation(String word) {
        return frenchDatabase.chercherTraduction(word);
    }
}

// Client Code
public class LanguageTranslatorApp {
    public static void main(String[] args) {
        // Interpreter Pattern - Using Expressions to Translate
        Expressions englishToSpanish = new EnglishToSpanish();
        Expressions englishToFrench = new EnglishToFrench();

        System.out.println("Translating 'hello' to Spanish: " + englishToSpanish.interpret("hello"));
        System.out.println("Translating 'goodbye' to French: " + englishToFrench.interpret("goodbye"));

        // Adapter Pattern - Integrating Language Databases
        SpanishDatabase spanishDatabase = new SpanishDatabase();
        LanguageDatabase spanishAdapter = new SpanishDatabaseAdapter(spanishDatabase);
        System.out.println("Adapter Translation 'hello' to Spanish: " + spanishAdapter.getTranslation("hello"));

        FrenchDatabase frenchDatabase = new FrenchDatabase();
        LanguageDatabase frenchAdapter = new FrenchDatabaseAdapter(frenchDatabase);
        System.out.println("Adapter Translation 'goodbye' to French: " + frenchAdapter.getTranslation("goodbye"));
    }
}

/**
Explanation:
1. `Expression` is the interpreter pattern interface that defines the `interpret()` method for translating words.
2. `EnglishToSpanish` and `EnglishToFrench` are concrete implementations of the `Expression` interface that provide translations for specific words.
3. `LanguageDatabase` is the target interface for the Adapter pattern, which provides the method `getTranslation()` for getting translations.
4. `SpanishDatabase` and `FrenchDatabase` are existing language databases that need to be integrated.
5. `SpanishDatabaseAdapter` and `FrenchDatabaseAdapter` adapt the existing databases to work with the `LanguageDatabase` interface.
6. `LanguageTranslatorApp` is the client code that demonstrates using both the Interpreter and Adapter patterns for translating words.
*/
