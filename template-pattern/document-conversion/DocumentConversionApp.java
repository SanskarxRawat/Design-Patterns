// Abstract Class
abstract class DocumentConversion {
    // Template Method
    public final void convertDocument() {
        loadDocument();
        convertFormat();
        saveDocument();
    }

    // Steps to be implemented by subclasses
    protected abstract void loadDocument();
    protected abstract void convertFormat();
    protected abstract void saveDocument();
}

// Concrete Class for PDF Conversion
class PDFConversion extends DocumentConversion {
    @Override
    protected void loadDocument() {
        System.out.println("Loading PDF document.");
    }

    @Override
    protected void convertFormat() {
        System.out.println("Converting document to PDF format.");
    }

    @Override
    protected void saveDocument() {
        System.out.println("Saving document as a PDF file.");
    }
}

// Concrete Class for Word Conversion
class WordConversion extends DocumentConversion {
    @Override
    protected void loadDocument() {
        System.out.println("Loading Word document.");
    }

    @Override
    protected void convertFormat() {
        System.out.println("Converting document to Word format.");
    }

    @Override
    protected void saveDocument() {
        System.out.println("Saving document as a Word file.");
    }
}

// Client Code
public class DocumentConversionApp {
    public static void main(String[] args) {
        DocumentConversion pdfConversion = new PDFConversion();
        DocumentConversion wordConversion = new WordConversion();

        System.out.println("Converting to PDF:");
        pdfConversion.convertDocument();

        System.out.println("\nConverting to Word:");
        wordConversion.convertDocument();
    }
}

/**
Explanation:
1. `DocumentConversion` is the abstract class that defines the template method `convertDocument()`, which outlines the steps to convert a document.
2. `PDFConversion` and `WordConversion` are concrete classes that implement the specific steps for loading, converting, and saving each document format.
3. `DocumentConversionApp` is the client that creates instances of different document conversions and processes them using the template method.
*/
