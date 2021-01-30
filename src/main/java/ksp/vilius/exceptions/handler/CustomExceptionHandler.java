package ksp.vilius.exceptions.handler;

import ksp.vilius.exceptions.ApplicationUserCreatingException;
import ksp.vilius.exceptions.ApplicationUserEmailsExistsException;
import ksp.vilius.exceptions.ApplicationUserUsernameExistsException;
import ksp.vilius.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> creatingUserException(ApplicationUserCreatingException exception, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> emailAlreadyExistsException(ApplicationUserEmailsExistsException exception, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> usernameAlreadyExistsException(ApplicationUserUsernameExistsException exception, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
