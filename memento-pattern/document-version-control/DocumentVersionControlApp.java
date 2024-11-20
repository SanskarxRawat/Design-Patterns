import java.util.ArrayList;
import java.util.List;

// Memento Class
class DocumentVersion {
    private String content;

    public DocumentVersion(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Originator Class
class Documents {
    private String content;

    public void write(String newContent) {
        this.content = newContent;
        System.out.println("Document content updated to: " + content);
    }

    public DocumentVersion save() {
        System.out.println("Saving document version: " + content);
        return new DocumentVersion(content);
    }

    public void restore(DocumentVersion version) {
        this.content = version.getContent();
        System.out.println("Document restored to version: " + content);
    }

    public String getContent() {
        return content;
    }
}

// Caretaker Class
class VersionControl {
    private List<DocumentVersion> versionHistory = new ArrayList<>();

    public void addVersion(DocumentVersion version) {
        versionHistory.add(version);
    }

    public DocumentVersion getVersion(int index) {
        return versionHistory.get(index);
    }
}

// Client Code
public class DocumentVersionControlApp {
    public static void main(String[] args) {
        Documents document = new Documents();
        VersionControl versionControl = new VersionControl();

        document.write("Version 1 - Initial Content");
        versionControl.addVersion(document.save());

        document.write("Version 2 - Added more content");
        versionControl.addVersion(document.save());

        document.write("Version 3 - Final changes");
        System.out.println("Current content: " + document.getContent());

        // Revert to previous version
        document.restore(versionControl.getVersion(1));
        System.out.println("After reverting: " + document.getContent());

        // Revert to initial version
        document.restore(versionControl.getVersion(0));
        System.out.println("After reverting to initial: " + document.getContent());
    }
}

/**
Explanation:
1. `DocumentVersion` is the memento class that stores the content of a document.
2. `Document` is the originator class that can create a version (memento) and restore its state from a version.
3. `VersionControl` is the caretaker class that manages the list of document versions.
4. `DocumentVersionControlApp` is the client that interacts with the document, saves its versions, and restores them when needed.
*/
