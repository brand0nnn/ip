package dino;

/**
 * Represents a todo task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description The description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for the todo
     *
     * @return A string "T" representing a Todo
     */
    public String getTypeIcon() {
        return "T";
    }
}
