// Flyweight Pattern - Book Content
import java.util.HashMap;
import java.util.Map;

interface BookContent {
    void displayContent();
}

class EBookContent implements BookContent {
    private String content;

    public EBookContent(String content) {
        this.content = content;
    }

    @Override
    public void displayContent() {
        System.out.println("Displaying book content: " + content);
    }
}

class BookContentFactory {
    private static final Map<String, BookContent> contentCache = new HashMap<>();

    public static BookContent getBookContent(String content) {
        if (!contentCache.containsKey(content)) {
            contentCache.put(content, new EBookContent(content));
            System.out.println("Creating new book content.");
        } else {
            System.out.println("Reusing existing book content.");
        }
        return contentCache.get(content);
    }
}

// Factory Pattern - Book Format
interface BookFormat {
    void displayFormat();
}

class PDFBookFormat implements BookFormat {
    @Override
    public void displayFormat() {
        System.out.println("Displaying book in PDF format.");
    }
}

class EPUBBookFormat implements BookFormat {
    @Override
    public void displayFormat() {
        System.out.println("Displaying book in EPUB format.");
    }
}

class BookFormatFactory {
    public static BookFormat createBookFormat(String format) {
        switch (format.toLowerCase()) {
            case "pdf":
                return new PDFBookFormat();
            case "epub":
                return new EPUBBookFormat();
            default:
                throw new IllegalArgumentException("Unknown book format: " + format);
        }
    }
}

// Client Code
public class EbookLibraryApp {
    public static void main(String[] args) {
        // Flyweight Pattern - Managing Book Content
        BookContent content1 = BookContentFactory.getBookContent("This is the content of Book A.");
        BookContent content2 = BookContentFactory.getBookContent("This is the content of Book B.");
        BookContent content3 = BookContentFactory.getBookContent("This is the content of Book A."); // Reuse existing content

        content1.displayContent();
        content2.displayContent();
        content3.displayContent();

        // Factory Pattern - Creating Book Formats
        BookFormat pdfFormat = BookFormatFactory.createBookFormat("pdf");
        BookFormat epubFormat = BookFormatFactory.createBookFormat("epub");

        System.out.println("\nDisplaying Book A in different formats:");
        pdfFormat.displayFormat();
        epubFormat.displayFormat();
    }
}

/**
Explanation:
1. `BookContent` is the Flyweight interface that defines the method `displayContent()` for displaying book content.
2. `EBookContent` is the concrete Flyweight class that implements the book content.
3. `BookContentFactory` is responsible for managing the cache of book content to reuse existing content when possible.
4. `BookFormat` is the product interface for the Factory pattern that defines the method `displayFormat()` for different book formats.
5. `PDFBookFormat` and `EPUBBookFormat` are concrete implementations of different book formats.
6. `BookFormatFactory` creates instances of book formats based on the input.
7. `EbookLibraryApp` is the client code that demonstrates the usage of Flyweight and Factory patterns for managing book content and formats.
*/
