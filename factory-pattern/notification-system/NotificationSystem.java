import java.util.Scanner;

/**
 * Factory Pattern: Implement a "Notification System" that generates different types of notifications (email, SMS, push) depending on user preference.
 */
// Product Interface
interface Notification {
    void notifyUser();
}

// Concrete Product Classes
class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an email notification...");
    }
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification...");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a push notification...");
    }
}

// Factory Class
class NotificationFactory {
    public static Notification createNotification(String type) {
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}

// Client Code
public class NotificationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the notification type (email, SMS, push): ");
        String type = scanner.nextLine();

        try {
            Notification notification = NotificationFactory.createNotification(type);
            notification.notifyUser();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
Explanation:
1. The `Notification` interface defines a common method `notifyUser()` for all notification types.
2. `EmailNotification`, `SMSNotification`, and `PushNotification` are concrete classes implementing the `Notification` interface.
3. The `NotificationFactory` class is responsible for creating instances of different notification types based on user input.
4. The `NotificationSystem` class is the client that asks the user for the type of notification and uses the factory to generate it.
*/
