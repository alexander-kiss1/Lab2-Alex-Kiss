import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        this.completeButton = new JButton("Complete");

        setLayout(new BorderLayout());
        updateDisplay();
        updateUrgency();
        // add complete button if event is completable
        if (event instanceof Completable) {
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                updateDisplay();
            });
            add(completeButton, BorderLayout.SOUTH);
        }
    }
    // adds updated info about event
    private void updateDisplay() {
        removeAll();
        StringBuilder displayText = new StringBuilder("<html><h3>" + event.getName() + "</h3>");
        displayText.append("Time: ").append(event.getDateTime().getTime()).append("<br>");
        // meeting details
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            displayText.append("Duration: ").append(meeting.getDuration()).append(" mins<br>");
            displayText.append("Location: ").append(meeting.getLocation()).append("<br>");
        }

        if (event instanceof Completable) {
            displayText.append("Status: ").append(((Completable) event).isComplete() ? "Complete" : "Incomplete").append("<br>");
        }

        displayText.append("</html>");
        JLabel label = new JLabel(displayText.toString());
        add(label, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    // updates the background of the colors in relation to the urgency of the event
    public void updateUrgency() {
        Calendar now = Calendar.getInstance();
        if (event.getDateTime().compareTo(now) < 0) {
            setBackground(Color.RED);
        } else if (event.getDateTime().compareTo(now) < 3 * 24 * 60 * 60 * 1000) { // Imminent (within 3 days)
            setBackground(Color.BLUE);
        } else {
            setBackground(Color.GREEN); // Distant
        }
    }
}

