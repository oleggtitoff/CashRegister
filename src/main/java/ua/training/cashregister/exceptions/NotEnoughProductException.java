package ua.training.cashregister.exceptions;

public class NotEnoughProductException extends RuntimeException {
    public NotEnoughProductException(String errorMessage) {
        super(errorMessage);
    }
}
