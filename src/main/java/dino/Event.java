package dino;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start date and an end date
 * <p>
 *     An event is a type of Task that includes additional information
 *     about when the begins and when it ends.
 * </p>
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String formattedStartDate;
    protected String formattedEndDate;

    /**
     * Constructor for Event
     *
     * @param description The description of the event
     * @param start The start date of the event
     * @param end The end date of the event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        formattedStartDate = startDate.format(formatter);
        formattedEndDate = endDate.format(formatter);
    }

    /**
     * Returns the type icon of an Event
     *
     * @return A string "E" indicating that this is an Event
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a formatted string representing the event information
     *
     * @return A string in the format " (from: {startDate} to: {endDate})"
     */
    public String getDate() {
        return " (from: " + this.formattedStartDate + " to: " + this.formattedEndDate + ")";
    }

    /**
     * Returns the start date of the event
     *
     * @return the start date as a string
     */
    public String getStartDate() { return this.startDate.toString(); }

    /**
     * Returns the end date of the event
     *
     * @return the end date as a string
     */
    public String getEndDate() { return this.endDate.toString(); }
}
