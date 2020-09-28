package exceptions;

public class InvalidLoginEmailException extends Exception{
    public InvalidLoginEmailException(String message) {
        super(message);
    }
}
