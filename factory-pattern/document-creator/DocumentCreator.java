public class DocumentCreator {

    public static void main(String[] args) {

        Document htmlDocument=DocumentFactory.createDocument("html");
        htmlDocument.create();
    }
}
