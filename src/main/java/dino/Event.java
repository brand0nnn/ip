package dino;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String formattedStartDate;
    protected String formattedEndDate;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        formattedStartDate = startDate.format(formatter);
        formattedEndDate = endDate.format(formatter);
    }

    public String getTypeIcon() {
        return "E";
    }

    public String getDate() {
        return " (from: " + this.formattedStartDate + " to: " + this.formattedEndDate + ")";
    }

    public String getStartDate() { return this.startDate.toString(); }

    public String getEndDate() { return this.endDate.toString(); }
}
