public class NotificationFactory {

    public static Notification createNotification(String type) {
        return switch (type){
            case "email" -> new EmailNotification();
            case "push"-> new PushNotification();
            case "sms"-> new SMSNotification();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
