import java.util.Date;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected Date dateTime;

    public abstract String getName();

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
