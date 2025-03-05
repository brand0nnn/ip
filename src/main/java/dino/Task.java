package dino;

/**
 * Represents a general task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task as an icon where "X" is completed and " " is uncompleted.
     *
     * @return "X" if task is done, a blank space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns an icon representing the task type
     *
     * @return a string representing the task type icon
     */
    public String getTypeIcon() {
        return " ";
    }

    /**
     * Returns the date associated with the task.
     *
     * @return a string representing the task date, or an empty string if none
     */
    public String getDate() {
        return "";
    }

    /**
     * Returns a start date associated with the task.
     *
     * @return a string representing the task start date, or an empty string if none
     */
    public String getStartDate() { return ""; }

    /**
     * Returns an end date associated with the task.
     *
     * @return a string representing the task end date, or an empty string if none
     */
    public String getEndDate() { return ""; }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

}
