package ksp.vilius.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationUserCreatingException extends RuntimeException {
    public ApplicationUserCreatingException(String message) {
        super(message);
    }
}
