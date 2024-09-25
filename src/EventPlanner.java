import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new EventListPanel());
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel eventListPanel) {
        // Create default events
        Calendar deadlineDate = Calendar.getInstance();
        deadlineDate.set(2024, Calendar.SEPTEMBER, 30, 23, 59);
        Deadline defaultDeadline = new Deadline("Default Deadline", deadlineDate);

        Calendar meetingStart = Calendar.getInstance();
        meetingStart.set(2024, Calendar.OCTOBER, 5, 10, 0);
        Calendar meetingEnd = Calendar.getInstance();
        meetingEnd.set(2024, Calendar.OCTOBER, 5, 11, 0);
        Meeting defaultMeeting = new Meeting("Default Meeting", meetingStart, meetingEnd, "Default Room");

        // Add default events to the event panel
        eventListPanel.addEvent(defaultDeadline);
        eventListPanel.addEvent(defaultMeeting);
    }
}

