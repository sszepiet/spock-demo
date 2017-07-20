package pl.sii.eu.spockdemo.error;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "Object with requested id could not be found";

    public NotFoundException() {
        super(MESSAGE);
    }
}
