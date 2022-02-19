package br.com.wefin.testepratico.utils;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.wefin.testepratico.exceptions.InvalidDocumentException;

@ControllerAdvice()
public class RestExceptionHandler {

    @ExceptionHandler(InvalidDocumentException.class)
    protected ResponseEntity<Object> handleInvalidDocumentProvided(InvalidDocumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
