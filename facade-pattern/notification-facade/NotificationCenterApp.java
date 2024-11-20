import java.util.ArrayList;
import java.util.List;

// Component Interface for Composite Pattern
interface Notifications {
    void send();
}

// Leaf Class for Individual Notifications
class EmailNotifications implements Notifications {
    private String recipient;

    public EmailNotifications(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void send() {
        System.out.println("Sending email notification to: " + recipient);
    }
}

class SMSNotifications implements Notifications {
    private String recipient;

    public SMSNotifications(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void send() {
        System.out.println("Sending SMS notification to: " + recipient);
    }
}

// Composite Class for Grouping Notifications
class NotificationGroup implements Notifications {
    private List<Notifications> notifications = new ArrayList<>();

    public void addNotification(Notifications notification) {
        notifications.add(notification);
    }

    public void removeNotification(Notifications notification) {
        notifications.remove(notification);
    }

    @Override
    public void send() {
        for (Notifications notification : notifications) {
            notification.send();
        }
    }
}

// Facade Class for Notification Center
class NotificationCenterFacade {
    private NotificationGroup emailGroup;
    private NotificationGroup smsGroup;

    public NotificationCenterFacade() {
        emailGroup = new NotificationGroup();
        smsGroup = new NotificationGroup();
    }

    public void addEmailNotification(String recipient) {
        emailGroup.addNotification(new EmailNotifications(recipient));
    }

    public void addSMSNotification(String recipient) {
        smsGroup.addNotification(new SMSNotifications(recipient));
    }

    public void sendAllNotifications() {
        System.out.println("Sending all email notifications:");
        emailGroup.send();
        System.out.println("\nSending all SMS notifications:");
        smsGroup.send();
    }
}

// Client Code
public class NotificationCenterApp {
    public static void main(String[] args) {
        NotificationCenterFacade notificationCenter = new NotificationCenterFacade();

        // Add email notifications
        notificationCenter.addEmailNotification("alice@example.com");
        notificationCenter.addEmailNotification("bob@example.com");

        // Add SMS notifications
        notificationCenter.addSMSNotification("+123456789");
        notificationCenter.addSMSNotification("+987654321");

        // Send all notifications
        notificationCenter.sendAllNotifications();
    }
}

/**
Explanation:
1. `Notification` is the component interface for the Composite pattern, which defines the `send()` method.
2. `EmailNotification` and `SMSNotification` are leaf classes that implement individual notifications.
3. `NotificationGroup` is the composite class that groups multiple notifications and sends them together.
4. `NotificationCenterFacade` is the Facade class that simplifies adding and sending notifications by hiding the complexity of the composite structure.
5. `NotificationCenterApp` is the client that uses the facade to interact with the notification center.
*/
