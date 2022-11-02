package model.exceptions;

public class SizeException extends IllegalArgumentException {
    public SizeException(final String message) {
        super(message);
    }
}
