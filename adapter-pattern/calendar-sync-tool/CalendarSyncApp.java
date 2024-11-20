import java.util.ArrayList;
import java.util.List;

// Adapter Pattern - Integrating Various Calendar Formats
interface CalendarFormat {
    void addEvent(String event);
}

class GoogleCalendar {
    public void addGoogleEvent(String event) {
        System.out.println("Adding event to Google Calendar: " + event);
    }
}

class OutlookCalendar {
    public void addOutlookEvent(String event) {
        System.out.println("Adding event to Outlook Calendar: " + event);
    }
}

class GoogleCalendarAdapter implements CalendarFormat {
    private GoogleCalendar googleCalendar;

    public GoogleCalendarAdapter(GoogleCalendar googleCalendar) {
        this.googleCalendar = googleCalendar;
    }

    @Override
    public void addEvent(String event) {
        googleCalendar.addGoogleEvent(event);
    }
}

class OutlookCalendarAdapter implements CalendarFormat {
    private OutlookCalendar outlookCalendar;

    public OutlookCalendarAdapter(OutlookCalendar outlookCalendar) {
        this.outlookCalendar = outlookCalendar;
    }

    @Override
    public void addEvent(String event) {
        outlookCalendar.addOutlookEvent(event);
    }
}

// Observer Pattern - Update Notifications
interface CalendarObserver {
    void update(String message);
}

class Users implements CalendarObserver {
    private String name;

    public Users(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

class CalendarSyncService {
    private List<CalendarObserver> observers = new ArrayList<>();

    public void subscribe(CalendarObserver observer) {
        observers.add(observer);
        System.out.println(observer.getClass().getSimpleName() + " has subscribed for calendar updates.");
    }

    public void unsubscribe(CalendarObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getClass().getSimpleName() + " has unsubscribed from calendar updates.");
    }

    public void notifyObservers(String message) {
        for (CalendarObserver observer : observers) {
            observer.update(message);
        }
    }

    public void syncEvent(String event) {
        System.out.println("Synchronizing event: " + event);
        notifyObservers("Event synchronized: " + event);
    }
}

// Client Code
public class CalendarSyncApp {
    public static void main(String[] args) {
        // Adapter Pattern - Integrating Various Calendar Formats
        GoogleCalendar googleCalendar = new GoogleCalendar();
        OutlookCalendar outlookCalendar = new OutlookCalendar();

        CalendarFormat googleAdapter = new GoogleCalendarAdapter(googleCalendar);
        CalendarFormat outlookAdapter = new OutlookCalendarAdapter(outlookCalendar);

        googleAdapter.addEvent("Team Meeting at 10 AM");
        outlookAdapter.addEvent("Project Deadline at 5 PM");

        // Observer Pattern - Update Notifications
        CalendarSyncService syncService = new CalendarSyncService();
        Users user1 = new Users("Alice");
        Users user2 = new Users("Bob");

        syncService.subscribe(user1);
        syncService.subscribe(user2);

        syncService.syncEvent("Team Meeting at 10 AM");
        syncService.syncEvent("Project Deadline at 5 PM");
    }
}

/**
Explanation:
1. `CalendarFormat` is the target interface for the Adapter pattern that defines the method `addEvent()` for adding events to various calendar formats.
2. `GoogleCalendar` and `OutlookCalendar` are existing calendar systems that need to be integrated.
3. `GoogleCalendarAdapter` and `OutlookCalendarAdapter` adapt the existing calendar systems to work with the `CalendarFormat` interface.
4. `CalendarObserver` is the observer interface that defines the `update()` method for receiving calendar updates.
5. `User` is the concrete observer that implements the `update()` method to receive calendar updates.
6. `CalendarSyncService` is the subject class that manages observer subscriptions and notifies them of calendar events.
7. `CalendarSyncApp` is the client code that demonstrates using both the Adapter pattern for integrating various calendar formats and the Observer pattern for update notifications.
*/
