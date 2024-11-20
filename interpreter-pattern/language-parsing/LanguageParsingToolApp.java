import java.util.ArrayList;
import java.util.List;

// Interpreter Pattern - Language Grammar
interface LanguageExpression {
    String interpret();
}

class NounExpression implements LanguageExpression {
    private String noun;

    public NounExpression(String noun) {
        this.noun = noun;
    }

    @Override
    public String interpret() {
        return noun;
    }
}

class VerbExpression implements LanguageExpression {
    private String verb;

    public VerbExpression(String verb) {
        this.verb = verb;
    }

    @Override
    public String interpret() {
        return verb;
    }
}

class AdjectiveExpression implements LanguageExpression {
    private String adjective;

    public AdjectiveExpression(String adjective) {
        this.adjective = adjective;
    }

    @Override
    public String interpret() {
        return adjective;
    }
}

// Composite Pattern - Sentence Structure
interface SentenceComponent {
    void add(SentenceComponent component);
    String construct();
}

class Word implements SentenceComponent {
    private LanguageExpression expression;

    public Word(LanguageExpression expression) {
        this.expression = expression;
    }

    @Override
    public void add(SentenceComponent component) {
        // Leaf node, add operation not supported
        throw new UnsupportedOperationException("Cannot add to a word.");
    }

    @Override
    public String construct() {
        return expression.interpret();
    }
}

class Sentence implements SentenceComponent {
    private List<SentenceComponent> components = new ArrayList<>();

    @Override
    public void add(SentenceComponent component) {
        components.add(component);
    }

    @Override
    public String construct() {
        StringBuilder sentence = new StringBuilder();
        for (SentenceComponent component : components) {
            sentence.append(component.construct()).append(" ");
        }
        return sentence.toString().trim() + ".";
    }
}

// Client Code
public class LanguageParsingToolApp {
    public static void main(String[] args) {
        // Interpreter Pattern - Defining Words
        LanguageExpression noun = new NounExpression("cat");
        LanguageExpression verb = new VerbExpression("jumps");
        LanguageExpression adjective = new AdjectiveExpression("quick");

        // Composite Pattern - Constructing Sentence
        SentenceComponent word1 = new Word(adjective);
        SentenceComponent word2 = new Word(noun);
        SentenceComponent word3 = new Word(verb);

        Sentence sentence = new Sentence();
        sentence.add(word1);
        sentence.add(word2);
        sentence.add(word3);

        System.out.println("Constructed Sentence: " + sentence.construct());
    }
}

/**
Explanation:
1. `Expression` is the interpreter interface that defines the `interpret()` method for interpreting different parts of language grammar.
2. `NounExpression`, `VerbExpression`, and `AdjectiveExpression` are concrete implementations of the `Expression` interface for different parts of speech.
3. `SentenceComponent` is the composite interface that defines methods for constructing sentence structures.
4. `Word` is the leaf class that represents individual words, and `Sentence` is the composite class that represents a collection of words.
5. `LanguageParsingToolApp` is the client code that demonstrates using both the Interpreter pattern for defining grammar and the Composite pattern for constructing sentences.
*/
