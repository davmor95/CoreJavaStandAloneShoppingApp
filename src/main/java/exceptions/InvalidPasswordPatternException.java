package exceptions;

public class InvalidPasswordPatternException extends Exception {
    public InvalidPasswordPatternException(String message) {
        super(message);
    }
}
