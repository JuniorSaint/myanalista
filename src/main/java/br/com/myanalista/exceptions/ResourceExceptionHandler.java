package br.com.myanalista.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    Date timeStamp = new Date();
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(timeStamp);
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(timeStamp);
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Problem to proceed");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NotAuthorizateException.class)
    public final ResponseEntity<StandardError> notAuthorized(NotAuthorizateException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(timeStamp);
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setError("Problem to proceed");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(MathOperationException.class)
    public final ResponseEntity<StandardError> mathOperationException(MathOperationException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(timeStamp);
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Problem to proceed");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @ExceptionHandler(ErrorUploadFileException.class)
    public final ResponseEntity<StandardError> mathOperationException(ErrorUploadFileException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(timeStamp);
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("Problem to upload file");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

}
