public class DinoException extends Exception {
    public DinoException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
