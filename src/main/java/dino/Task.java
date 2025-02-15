package dino;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTypeIcon() {
        return " ";
    }

    public String getDate() {
        return "";
    }

    public String getStartDate() { return ""; }

    public String getEndDate() { return ""; }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

}
