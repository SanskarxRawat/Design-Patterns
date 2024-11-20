import java.util.ArrayList;
import java.util.List;

// Composite Pattern - Organizing Notifications
interface NotificationComponent {
    void display();
}

class SingleNotification implements NotificationComponent {
    private String message;

    public SingleNotification(String message) {
        this.message = message;
    }

    @Override
    public void display() {
        System.out.println("Notification: " + message);
    }
}

class NotificationGroups implements NotificationComponent {
    private List<NotificationComponent> notifications = new ArrayList<>();

    public void add(NotificationComponent notification) {
        notifications.add(notification);
    }

    public void remove(NotificationComponent notification) {
        notifications.remove(notification);
    }

    @Override
    public void display() {
        for (NotificationComponent notification : notifications) {
            notification.display();
        }
    }
}

// Observer Pattern - Real-Time Updates
interface NotificationObserver {
    void update(String message);
}

class NotificationUser implements NotificationObserver {
    private String name;

    public NotificationUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

class NotificationAggregator {
    private List<NotificationObserver> observers = new ArrayList<>();

    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
        System.out.println(observer.getClass().getSimpleName() + " has subscribed for notifications.");
    }

    public void unsubscribe(NotificationObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getClass().getSimpleName() + " has unsubscribed from notifications.");
    }

    public void notifyObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addNotification(NotificationComponent notification) {
        System.out.println("Adding new notification: " + notification);
        notifyObservers("New notification added: " + notification);
    }
}

// Client Code
public class NotificationAggregatorApp {
    public static void main(String[] args) {
        // Composite Pattern - Organizing Notifications
        SingleNotification notification1 = new SingleNotification("New message from Alice.");
        SingleNotification notification2 = new SingleNotification("Meeting at 3 PM.");
        SingleNotification notification3 = new SingleNotification("Update available for your app.");

        NotificationGroups group = new NotificationGroups();
        group.add(notification1);
        group.add(notification2);
        group.add(notification3);

        System.out.println("Displaying Notifications Group:");
        group.display();

        // Observer Pattern - Real-Time Updates
        NotificationAggregator aggregator = new NotificationAggregator();
        NotificationUser user1 = new NotificationUser("Bob");
        NotificationUser user2 = new NotificationUser("Carol");

        aggregator.subscribe(user1);
        aggregator.subscribe(user2);

        System.out.println("\nAdding new notification to aggregator:");
        aggregator.addNotification(notification1);
    }
}

/**
Explanation:
1. `NotificationComponent` is the component interface for the Composite pattern that defines the `display()` method for displaying notifications.
2. `SingleNotification` is the leaf class that represents an individual notification.
3. `NotificationGroup` is the composite class that can contain multiple notifications and display them as a group.
4. `NotificationObserver` is the observer interface that defines the `update()` method for receiving real-time updates.
5. `User` is the concrete observer that implements the `update()` method to receive notifications.
6. `NotificationAggregator` is the subject class that manages observer subscriptions and notifies them of new notifications.
7. `NotificationAggregatorApp` is the client code that demonstrates using both the Composite pattern for organizing notifications and the Observer pattern for providing real-time updates.
*/
