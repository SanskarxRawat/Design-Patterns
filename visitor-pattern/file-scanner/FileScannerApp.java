// Element Interface
interface Files {
    void accept(FileVisitor visitor);
}

// Concrete Elements
class TextFile implements Files {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

class ImageFile implements Files {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor Interface
interface FileVisitor {
    void visit(TextFile textFile);
    void visit(ImageFile imageFile);
}

// Concrete Visitor Class for Virus Scanning
class VirusScanner implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Scanning text file for viruses: " + textFile.getName());
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Scanning image file for viruses: " + imageFile.getName());
    }
}

// Concrete Visitor Class for Archiving
class Archiver implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Archiving text file: " + textFile.getName());
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Archiving image file: " + imageFile.getName());
    }
}

// Client Code
public class FileScannerApp {
    public static void main(String[] args) {
        Files textFile = new TextFile("document.txt");
        Files imageFile = new ImageFile("picture.jpg");

        FileVisitor virusScanner = new VirusScanner();
        FileVisitor archiver = new Archiver();

        System.out.println("Performing virus scan:");
        textFile.accept(virusScanner);
        imageFile.accept(virusScanner);

        System.out.println("\nPerforming archiving:");
        textFile.accept(archiver);
        imageFile.accept(archiver);
    }
}

/**
Explanation:
1. `File` is the element interface that declares the `accept()` method for accepting visitors.
2. `TextFile` and `ImageFile` are concrete elements that implement the `accept()` method to accept visitors.
3. `FileVisitor` is the visitor interface that declares the `visit()` methods for different file types.
4. `VirusScanner` and `Archiver` are concrete visitors that perform different actions on files (scanning for viruses, archiving).
5. `FileScannerApp` is the client that creates files and uses visitors to perform different actions on them.
*/
