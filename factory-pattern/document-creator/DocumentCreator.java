import java.util.Scanner;

/**
 * Factory Pattern: Design a "Document Creator" where different document types (PDF, Word, HTML) are created based on user input.
 */
// Product Interface
interface Document {
    void create();
}

// Concrete Product Classes
class PDFDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a PDF document...");
    }
}

class WordDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a Word document...");
    }
}

class HTMLDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating an HTML document...");
    }
}

// Factory Class
class DocumentFactory {
    public static Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "pdf" -> new PDFDocument();
            case "word" -> new WordDocument();
            case "html" -> new HTMLDocument();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}

// Client Code
public class DocumentCreator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the document type (PDF, Word, HTML): ");
        String type = scanner.nextLine();

        try {
            Document document = DocumentFactory.createDocument(type);
            document.create();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
Explanation:
1. The `Document` interface acts as a common product interface for all document types.
2. `PDFDocument`, `WordDocument`, and `HTMLDocument` are concrete implementations of the `Document` interface.
3. The `DocumentFactory` is a factory class that is responsible for creating instances of different document types based on the user input.
4. The `DocumentCreator` class is the client that interacts with the user and utilizes the factory to create documents.
*/
