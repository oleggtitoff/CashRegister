package ua.training.cashregister.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
