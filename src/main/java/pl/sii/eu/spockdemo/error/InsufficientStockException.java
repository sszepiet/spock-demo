package pl.sii.eu.spockdemo.error;

public class InsufficientStockException extends RuntimeException {
    private static final String MESSAGE = "We're out of stock";

    public InsufficientStockException() {
        super(MESSAGE);
    }
}
