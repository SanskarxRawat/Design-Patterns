/**
 * Proxy Pattern: Build a "Virtual Proxy" for an image gallery that loads images on demand to improve performance.
 */

// Subject Interface
interface Image {
    void display();
}

// Real Subject Class
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

// Proxy Class
class VirtualImageProxy implements Image {
    private String fileName;
    private RealImage realImage;

    public VirtualImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Client Code
public class ImageGalleryApp {
    public static void main(String[] args) {
        Image image1 = new VirtualImageProxy("Photo1.jpg");
        Image image2 = new VirtualImageProxy("Photo2.jpg");

        System.out.println("Image gallery loaded. No images displayed yet.");

        // Images are loaded and displayed on demand
        image1.display();
        image2.display();
        image1.display(); // No loading this time, as the image is already loaded
    }
}

/**
Explanation:
1. `Image` is the subject interface that defines the `display()` method for images.
2. `RealImage` is the real subject class that represents the actual image, loading it from disk and displaying it.
3. `VirtualImageProxy` is the proxy class that delays the loading of the real image until it is needed, thus improving performance.
4. `ImageGalleryApp` is the client that interacts with the `VirtualImageProxy` to load and display images only when required.
*/
