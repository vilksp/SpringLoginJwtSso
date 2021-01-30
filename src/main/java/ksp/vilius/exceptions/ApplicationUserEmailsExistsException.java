package ksp.vilius.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationUserEmailsExistsException extends RuntimeException {

    public ApplicationUserEmailsExistsException(String message) {
        super(message);
    }
}
