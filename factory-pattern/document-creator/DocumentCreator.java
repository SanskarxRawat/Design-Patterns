public class DocumentCreator {

    /**
     * Explanation:
     * 1. The `Document` interface acts as a common product interface for all document types.
     * 2. `PDFDocument`, `WordDocument`, and `HTMLDocument` are concrete implementations of the `Document` interface.
     * 3. The `DocumentFactory` is a factory class that is responsible for creating instances of different document types based on the user input.
     * 4. The `DocumentCreator` class is the client that interacts with the user and utilizes the factory to create documents.
     */
    public static void main(String[] args) {

        Document htmlDocument=DocumentFactory.createDocument("html");
        htmlDocument.create();
    }
}
