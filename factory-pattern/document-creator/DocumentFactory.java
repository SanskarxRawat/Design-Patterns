public class DocumentFactory {

    public static Document createDocument(String type) {

        return switch (type) {
            case "html" -> new HTMLDocument();
            case "pdf" -> new PDFDocument();
            case "word" -> new WordDocument();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}
