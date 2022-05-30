package br.com.myanalista.exceptions;

public class ErrorToConnect extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ErrorToConnect(String msg) {
    super(msg);
  }
}
