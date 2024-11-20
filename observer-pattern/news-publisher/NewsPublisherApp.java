import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface NewsPublisher {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String article);
}

// Concrete Subject Class
class NewsAgency implements NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println(subscriber.getName() + " has subscribed.");
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getName() + " has unsubscribed.");
    }

    @Override
    public void notifySubscribers(String article) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(article);
        }
    }

    public void publishArticle(String article) {
        System.out.println("Publishing article: " + article);
        notifySubscribers(article);
    }
}

// Observer Interface
interface Subscriber {
    void update(String article);
    String getName();
}

// Concrete Observer Class
class ConcreteSubscriber implements Subscriber {
    private String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(name + " received notification: New article published - " + article);
    }

    @Override
    public String getName() {
        return name;
    }
}

// Client Code
public class NewsPublisherApp {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();

        Subscriber subscriber1 = new ConcreteSubscriber("Alice");
        Subscriber subscriber2 = new ConcreteSubscriber("Bob");
        Subscriber subscriber3 = new ConcreteSubscriber("Charlie");

        newsAgency.subscribe(subscriber1);
        newsAgency.subscribe(subscriber2);

        newsAgency.publishArticle("Breaking News: Observer Pattern in Java");

        newsAgency.unsubscribe(subscriber1);

        newsAgency.publishArticle("Update: Java Design Patterns are Powerful");

        newsAgency.subscribe(subscriber3);
        newsAgency.publishArticle("New Article: Learn Observer Pattern Today");
    }
}

/**
Explanation:
1. `NewsPublisher` is the subject interface that defines methods for subscribing, unsubscribing, and notifying subscribers.
2. `NewsAgency` is the concrete subject that maintains a list of subscribers and notifies them when a new article is published.
3. `Subscriber` is the observer interface that defines the `update()` method for receiving updates from the publisher.
4. `ConcreteSubscriber` is the concrete observer that implements the `update()` method to receive notifications.
5. `NewsPublisherApp` is the client that creates the news agency, subscribes users, and publishes articles.
*/
