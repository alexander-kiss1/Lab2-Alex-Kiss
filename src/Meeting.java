import java.util.Calendar;

public class Meeting extends Event implements Completable {
    private Calendar endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String name, Calendar dateTime, Calendar endDateTime, String location) {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;
    }
    // gets name of meeting and returns
    @Override
    public String getName() {
        return name;
    }
    // gets the date of meeting and returns
    @Override
    public Calendar getDateTime() {
        return dateTime;
    }
    // returns calendar object
    public Calendar getEndTime() {
        return endDateTime;
    }

    public int getDuration() {
        return (int) (endDateTime.getTimeInMillis() - dateTime.getTimeInMillis()) / (1000 * 60); // Duration in minutes
    }
    // gets location of meeting
    public String getLocation() {
        return location;
    }
    // sets meeting time
    public void setEndTime(Calendar endDateTime) {
        this.endDateTime = endDateTime;
    }
    // sets location
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
