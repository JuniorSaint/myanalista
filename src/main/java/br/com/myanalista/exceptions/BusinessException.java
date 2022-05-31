package br.com.myanalista.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public BusinessException(String msg) {
    super(msg);
  }
}
