package dino;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTypeIcon() {
        return "T";
    }
}
