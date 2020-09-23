package exceptions;

public class InvalidEmailPatternException extends Exception {
    public InvalidEmailPatternException(String message) {
        super(message);
    }
}
