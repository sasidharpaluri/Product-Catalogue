package dev.sasidhar.productcatalogue.Exceptions;



public class LoginExpired extends RuntimeException {
    public LoginExpired(String message) {
        super(message);
    }
}
