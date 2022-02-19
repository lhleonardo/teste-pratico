package br.com.wefin.testepratico.config;

import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wefin.testepratico.exceptions.DuplicateDocumentException;
import br.com.wefin.testepratico.exceptions.InvalidDocumentException;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {
    @Autowired
    private DefaultErrorAttributes defaultErrorAttributes;

    @ExceptionHandler(value = { InvalidDocumentException.class, DuplicateDocumentException.class,
            EntityNotFoundException.class })
    public ResponseEntity<Object> handlePackedTemplateNotRecodableException(final RuntimeException exception,
            final WebRequest webRequest) {
        // build the default error response
        webRequest.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(),
                RequestAttributes.SCOPE_REQUEST);

        final Map<String, Object> errorAttributes = defaultErrorAttributes.getErrorAttributes(webRequest,
                ErrorAttributeOptions.defaults());

        errorAttributes.put("message", exception.getMessage());
        errorAttributes.put("type", exception.getClass().getSimpleName());
        // return the error response with the specific response code
        return handleExceptionInternal(exception, errorAttributes, new HttpHeaders(), HttpStatus.BAD_REQUEST,
                webRequest);
    }
}
