package dino;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date.
 * <p>
 *     A Deadline is a type of Task that includes additional information
 *     about when the task must be completed.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDate dateAndTime;
    protected String formattedDate;

    /**
     * Constructor for Deadline
     *
     * @param description The description of the deadline
     * @param date The end date of the deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.dateAndTime = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        formattedDate = date.format(formatter);
    }

    /**
     * Returns the type icon for a Deadline
     *
     * @return A string "D" indicating that this is a Deadline
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a formatted string representing the deadline information
     *
     * @return A string in the format " (by: {dateAndTime})"
     */
    public String getDate() {
        return " (by: " + this.formattedDate + ")";
    }

    /**
     * Returns the due date of the deadline
     *
     * @return the due date as a string
     */
    public String getEndDate() { return this.dateAndTime.toString(); }
}
