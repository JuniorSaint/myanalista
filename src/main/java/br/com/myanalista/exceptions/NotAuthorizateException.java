package br.com.myanalista.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAuthorizateException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotAuthorizateException(String message){
        super(message);
    }
}


