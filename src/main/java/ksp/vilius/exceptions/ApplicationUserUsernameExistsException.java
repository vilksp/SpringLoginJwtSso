package ksp.vilius.exceptions;

public class ApplicationUserUsernameExistsException extends RuntimeException {
    public ApplicationUserUsernameExistsException(String message) {
        super(message);
    }
}
