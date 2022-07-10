package br.com.myanalista.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    public StandardError() {
    }
}
