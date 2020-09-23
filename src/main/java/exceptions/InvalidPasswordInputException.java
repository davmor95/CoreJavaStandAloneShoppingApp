package exceptions;

public class InvalidPasswordInputException extends Exception {
    public InvalidPasswordInputException(String message) {
        super(message);
    }
}
