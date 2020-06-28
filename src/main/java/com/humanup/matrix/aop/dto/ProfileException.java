package com.humanup.matrix.aop.dto;

public class ProfileException extends HttpException {
  public ProfileException(String message) {
    super(message);
  }

  public ProfileException() {
    super();
  }

  @Override
  public String getMessage() {
    return "Cannot Create Profile";
  }
}
