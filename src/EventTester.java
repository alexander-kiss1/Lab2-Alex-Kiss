import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventTester {
    public static void main(String... args) {
        List<Event> events = new ArrayList<>();

        // Create Calendar instances for deadlines
        Calendar deadline1Date = Calendar.getInstance();
        deadline1Date.set(2024, Calendar.SEPTEMBER, 30, 23, 59);
        Deadline deadline1 = new Deadline("Submit Assignment", deadline1Date);

        Calendar deadline2Date = Calendar.getInstance();
        deadline2Date.set(2024, Calendar.OCTOBER, 5, 17, 0);
        Deadline deadline2 = new Deadline("Finish Project", deadline2Date);

        // Create Calendar instances for meetings
        Calendar meeting1Start = Calendar.getInstance();
        meeting1Start.set(2024, Calendar.SEPTEMBER, 28, 10, 0);
        Calendar meeting1End = Calendar.getInstance();
        meeting1End.set(2024, Calendar.SEPTEMBER, 28, 11, 0);
        Meeting meeting1 = new Meeting("Team Meeting", meeting1Start, meeting1End, "Conference Room A");

        Calendar meeting2Start = Calendar.getInstance();
        meeting2Start.set(2024, Calendar.SEPTEMBER, 29, 14, 0);
        Calendar meeting2End = Calendar.getInstance();
        meeting2End.set(2024, Calendar.SEPTEMBER, 29, 15, 0);
        Meeting meeting2 = new Meeting("Client Meeting", meeting2Start, meeting2End, "Zoom");

        // Add events to the list
        events.add(deadline1);
        events.add(deadline2);
        events.add(meeting1);
        events.add(meeting2);

        // Display all events
        System.out.println("Events before completion:");
        for (Event event : events) {
            System.out.println(event.getName() + " - " + event.getDateTime().getTime());
        }

        // Complete some events
        deadline1.complete();
        meeting1.complete();

        // Display completed events
        System.out.println("\nCompleted events:");
        for (Event event : events) {
            if (event instanceof Completable && ((Completable) event).isComplete()) {
                System.out.println(event.getName() + " is complete.");
            }
        }

        // Sort events by dateTime
        events.sort(Event::compareTo);

        // Display sorted events
        System.out.println("\nSorted events:");
        for (Event event : events) {
            System.out.println(event.getName() + " - " + event.getDateTime().getTime());
        }
    }
}
