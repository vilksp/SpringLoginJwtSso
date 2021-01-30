package ksp.vilius.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationUserEmailsExistsException extends RuntimeException {

    public ApplicationUserEmailsExistsException(String message) {
        super(message);
    }
}
