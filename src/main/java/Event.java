public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String description, String start, String end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
    }

    public String getTypeIcon() {
        return "E";
    }

    public String getDate() {
        return " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
