import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterCompleted;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel();
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date"});
        sortDropDown.addActionListener(e -> updateDisplay());
        controlPanel.add(sortDropDown);

        filterCompleted = new JCheckBox("Hide Completed");
        filterCompleted.addActionListener(e -> updateDisplay());
        controlPanel.add(filterCompleted);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> new AddEventModal(this).setVisible(true));
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);

        // Display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(displayPanel, BorderLayout.CENTER);

        // Add default events
        EventPlanner.addDefaultEvents(this);
        updateDisplay();
    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    public void updateDisplay() {
        displayPanel.removeAll();

        ArrayList<Event> filteredEvents = new ArrayList<>(events);
        if (filterCompleted.isSelected()) {
            filteredEvents.removeIf(event -> event instanceof Completable && ((Completable) event).isComplete());
        }

        // Sort events
        filteredEvents.sort((e1, e2) -> {
            if (sortDropDown.getSelectedIndex() == 0) { // Sort by Name
                return e1.getName().compareTo(e2.getName());
            } else { // Sort by Date
                return e1.getDateTime().compareTo(e2.getDateTime());
            }
        });

        for (Event event : filteredEvents) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }

        revalidate();
        repaint();
    }
}

