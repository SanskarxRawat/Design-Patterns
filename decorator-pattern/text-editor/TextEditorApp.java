/**
 * Decorator Pattern: Implement a "Text Editor" where users can apply different styles (bold, italic, underline) to text.
 */

// Component Interface
interface Text {
    String getContent();
}

// Concrete Component
class PlainText implements Text {
    private String content;

    public PlainText(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}

// Abstract Decorator Class
abstract class TextDecorator implements Text {
    protected Text text;

    public TextDecorator(Text text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text.getContent();
    }
}

// Concrete Decorators
class BoldText extends TextDecorator {
    public BoldText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<b>" + text.getContent() + "</b>";
    }
}

class ItalicText extends TextDecorator {
    public ItalicText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<i>" + text.getContent() + "</i>";
    }
}

class UnderlineText extends TextDecorator {
    public UnderlineText(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<u>" + text.getContent() + "</u>";
    }
}

// Client Code
public class TextEditorApp {
    public static void main(String[] args) {
        Text text = new PlainText("Hello, World!");
        System.out.println(text.getContent());

        text = new BoldText(text);
        System.out.println(text.getContent());

        text = new ItalicText(text);
        System.out.println(text.getContent());

        text = new UnderlineText(text);
        System.out.println(text.getContent());
    }
}

/**
Explanation:
1. `Text` is the component interface that defines the method `getContent()`.
2. `PlainText` is the concrete component that represents the basic text without any styles.
3. `TextDecorator` is the abstract decorator class that implements `Text` and delegates its method to the component it decorates.
4. `BoldText`, `ItalicText`, and `UnderlineText` are concrete decorators that add specific styles to the text.
5. `TextEditorApp` is the client that creates text and applies different styles using the decorator pattern.
*/
