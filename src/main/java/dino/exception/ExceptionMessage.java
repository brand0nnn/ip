package dino.exception;

public enum ExceptionMessage {
    EMPTY_COMMAND("INPUT ERROR: Task cannot be empty."),
    ITEM_MARKED("Oops, this item has already been marked!"),
    ITEM_UNMARKED("Oops, this item has already been unmarked!"),
    NO_BY_DATE("INPUT ERROR: No deadline end date specified"),
    NO_TO_DATE("INPUT ERROR: No event end date specified"),
    NO_FROM_DATE("INPUT ERROR: No event start date specified"),
    INVALID_INDEX("INPUT ERROR: Index specified does not exist"),
    ILLEGAL_CHARACTER("ILLEGAL CHARACTER: Do not use | in any commands"),
    INVALID_COMMAND("INPUT ERROR: Invalid command entered");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
