package com.humanup.matrix.aop.dto;

public class PersonException extends HttpException {
  public PersonException(String message) {
    super(message);
  }

  public PersonException() {
    super();
  }

  @Override
  public String getMessage() {
    return "Cannot Create Person";
  }
}
