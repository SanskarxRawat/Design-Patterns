public class NotificationCreator {

    public static void main(String[] args) {
        Notification smsNotification=NotificationFactory.createNotification("sms");
        smsNotification.notifyUser();
    }
}
