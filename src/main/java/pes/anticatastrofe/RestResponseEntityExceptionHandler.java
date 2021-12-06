package pes.anticatastrofe;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.security.auth.login.CredentialException;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleIllegalArgumentException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "IllegalArgument";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalIllegalState(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "IllegalState";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = { CredentialException.class})
    protected ResponseEntity<Object> handleCredentialException(
            CredentialException ex, WebRequest request) {
        String bodyOfResponse = "Bad credentials, no confidential info for you";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value
            = { EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleConflictEmptyResultDataAccessException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Data object not exists so no operation took place";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value
            = { DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleConflictDataIntegrityViolationException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Dependency not fulfilled, does this object need another one which is not created?";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FAILED_DEPENDENCY, request);
    }

    @ExceptionHandler(value
            = { DuplicateKeyException.class})
    protected ResponseEntity<Object> handleConflictDuplicateKeyException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Data object already exists";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.ALREADY_REPORTED, request);
    }
}