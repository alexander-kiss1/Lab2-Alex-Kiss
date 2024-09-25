import java.util.Calendar;

public class Deadline extends Event implements Completable {
    private boolean complete;

    public Deadline(String name, Calendar dateTime) {
        super(name, dateTime);
        this.complete = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Calendar getDateTime() {
        return dateTime;
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
