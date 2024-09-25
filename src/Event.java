import java.util.Calendar;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected Calendar dateTime;

    public Event(String name, Calendar dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public abstract String getName();
    public abstract Calendar getDateTime();
    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
