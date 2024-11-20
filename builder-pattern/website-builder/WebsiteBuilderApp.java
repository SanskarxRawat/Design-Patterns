// Builder Pattern - Building Site Templates
class Website {
    private String header;
    private String footer;
    private String mainContent;
    private String sidebar;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public void setSidebar(String sidebar) {
        this.sidebar = sidebar;
    }

    public void showWebsite() {
        System.out.println("Website Structure:");
        System.out.println("Header: " + header);
        System.out.println("Main Content: " + mainContent);
        System.out.println("Sidebar: " + sidebar);
        System.out.println("Footer: " + footer);
    }
}

abstract class WebsiteBuilder {
    protected Website website;

    public void createNewWebsite() {
        website = new Website();
    }

    public Website getWebsite() {
        return website;
    }

    public abstract void buildHeader();
    public abstract void buildFooter();
    public abstract void buildMainContent();
    public abstract void buildSidebar();
}

class BlogWebsiteBuilder extends WebsiteBuilder {
    @Override
    public void buildHeader() {
        website.setHeader("Blog Header");
    }

    @Override
    public void buildFooter() {
        website.setFooter("Blog Footer");
    }

    @Override
    public void buildMainContent() {
        website.setMainContent("Blog Articles and Posts");
    }

    @Override
    public void buildSidebar() {
        website.setSidebar("Blog Categories and Archives");
    }
}

class ECommerceWebsiteBuilder extends WebsiteBuilder {
    @Override
    public void buildHeader() {
        website.setHeader("E-Commerce Header");
    }

    @Override
    public void buildFooter() {
        website.setFooter("E-Commerce Footer");
    }

    @Override
    public void buildMainContent() {
        website.setMainContent("Product Listings and Descriptions");
    }

    @Override
    public void buildSidebar() {
        website.setSidebar("Product Categories and Filters");
    }
}

class WebsiteDirector {
    private WebsiteBuilder builder;

    public void setBuilder(WebsiteBuilder builder) {
        this.builder = builder;
    }

    public Website constructWebsite() {
        builder.createNewWebsite();
        builder.buildHeader();
        builder.buildFooter();
        builder.buildMainContent();
        builder.buildSidebar();
        return builder.getWebsite();
    }
}

// Decorator Pattern - Adding Plugins to Website
interface WebsitePlugin {
    void integrate();
}

class BasicWebsite implements WebsitePlugin {
    @Override
    public void integrate() {
        System.out.println("Basic website setup.");
    }
}

abstract class PluginDecorator implements WebsitePlugin {
    protected WebsitePlugin decoratedWebsite;

    public PluginDecorator(WebsitePlugin website) {
        this.decoratedWebsite = website;
    }

    @Override
    public void integrate() {
        decoratedWebsite.integrate();
    }
}

class SEOPlugin extends PluginDecorator {
    public SEOPlugin(WebsitePlugin website) {
        super(website);
    }

    @Override
    public void integrate() {
        super.integrate();
        addSEO();
    }

    private void addSEO() {
        System.out.println("Adding SEO optimization plugin.");
    }
}

class SecurityPlugin extends PluginDecorator {
    public SecurityPlugin(WebsitePlugin website) {
        super(website);
    }

    @Override
    public void integrate() {
        super.integrate();
        addSecurity();
    }

    private void addSecurity() {
        System.out.println("Adding security features plugin.");
    }
}

// Client Code
public class WebsiteBuilderApp {
    public static void main(String[] args) {
        // Builder Pattern - Creating Website Templates
        WebsiteDirector director = new WebsiteDirector();

        WebsiteBuilder blogBuilder = new BlogWebsiteBuilder();
        director.setBuilder(blogBuilder);
        Website blogWebsite = director.constructWebsite();
        blogWebsite.showWebsite();

        System.out.println("\nBuilding E-Commerce Website:");
        WebsiteBuilder ecomBuilder = new ECommerceWebsiteBuilder();
        director.setBuilder(ecomBuilder);
        Website ecomWebsite = director.constructWebsite();
        ecomWebsite.showWebsite();

        // Decorator Pattern - Adding Plugins to Website
        System.out.println("\nIntegrating Plugins into Website:");
        WebsitePlugin basicWebsite = new BasicWebsite();
        WebsitePlugin seoWebsite = new SEOPlugin(basicWebsite);
        WebsitePlugin secureSeoWebsite = new SecurityPlugin(seoWebsite);

        secureSeoWebsite.integrate();
    }
}

/**
Explanation:
1. `WebsiteBuilder` is the abstract builder class for creating different parts of a website.
2. `BlogWebsiteBuilder` and `ECommerceWebsiteBuilder` are concrete builders that implement specific website templates.
3. `WebsiteDirector` directs the construction process of the website using the builder.
4. `WebsitePlugin` is the component interface for the Decorator pattern that defines the `integrate()` method for adding plugins.
5. `BasicWebsite` is the concrete component representing a basic website setup.
6. `PluginDecorator` is the abstract base class for adding functionalities to the website.
7. `SEOPlugin` and `SecurityPlugin` are concrete decorators that add specific plugins to the website.
8. `WebsiteBuilderApp` is the client code that demonstrates using both the Builder pattern for creating website templates and the Decorator pattern for adding plugins to the websites.
*/
