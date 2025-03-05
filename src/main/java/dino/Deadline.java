package dino;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dateAndTime;
    protected String formattedDate;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.dateAndTime = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        formattedDate = date.format(formatter);
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDate() {
        return " (by: " + this.formattedDate + ")";
    }

    public String getEndDate() { return this.dateAndTime.toString(); }
}
