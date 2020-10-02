package exceptions;

public class CurrentUserIsNullException extends Exception {
    public CurrentUserIsNullException(String message) {
        super(message);
    }
}
