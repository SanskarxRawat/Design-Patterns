import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern: Design a "File System" with folders and files where operations can be performed on individual files or entire folders.
 */

// Component Interface
interface FileSystemComponent {
    void showDetails();
}

// Leaf Class
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite Class
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

// Client Code
public class FileSystemApp {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("Document1.txt");
        FileSystemComponent file2 = new File("Photo1.jpg");
        FileSystemComponent file3 = new File("Video1.mp4");

        Folder folder1 = new Folder("My Documents");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        Folder folder2 = new Folder("My Media");
        folder2.addComponent(file3);

        Folder rootFolder = new Folder("Root Folder");
        rootFolder.addComponent(folder1);
        rootFolder.addComponent(folder2);

        rootFolder.showDetails();
    }
}

/**
Explanation:
1. `FileSystemComponent` is the component interface that defines the `showDetails()` method for both files and folders.
2. `File` is the leaf class that represents individual files and implements the `showDetails()` method.
3. `Folder` is the composite class that can contain multiple files and/or folders. It implements the `showDetails()` method and manages its children components.
4. `FileSystemApp` is the client that creates files and folders and performs operations on the entire structure.
*/
