package dino;

public class Deadline extends Task {
    protected String dateAndTime;

    public Deadline(String description, String date) {
        super(description);
        this.dateAndTime = date;
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDate() {
        return " (by: " + this.dateAndTime + ")";
    }
}
