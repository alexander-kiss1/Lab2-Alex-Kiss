import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField dateField;
    private JTextField timeField;
    private JComboBox<String> typeComboBox;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setModal(true);
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        // Type selection
        typeComboBox = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        add(new JLabel("Event Type:"));
        add(typeComboBox);

        // Name input
        nameField = new JTextField();
        add(new JLabel("Event Name:"));
        add(nameField);

        // Location input (only for Meeting)
        locationField = new JTextField();
        add(new JLabel("Location (for Meeting):"));
        add(locationField);

        // Date input
        dateField = new JTextField("YYYY-MM-DD");
        add(new JLabel("Date (YYYY-MM-DD):"));
        add(dateField);

        // Time input
        timeField = new JTextField("HH:MM");
        add(new JLabel("Time (HH:MM):"));
        add(timeField);

        // Add button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
                dispose();
            }
        });
        add(addButton);

        setVisible(true);
    }
    // collects data from user and then adds the event to the calendar
    private void addEvent() {
        String name = nameField.getText();
        String[] dateParts = dateField.getText().split("-");
        String[] timeParts = timeField.getText().split(":");

        Calendar dateTime = Calendar.getInstance();
        dateTime.set(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[2]),
                Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));

        if (typeComboBox.getSelectedItem().equals("Deadline")) {
            eventListPanel.addEvent(new Deadline(name, dateTime));
        } else {
            Calendar endTime = (Calendar) dateTime.clone();
            endTime.add(Calendar.HOUR, 1); // Default meeting duration of 1 hour
            eventListPanel.addEvent(new Meeting(name, dateTime, endTime, locationField.getText()));
        }
    }
}

