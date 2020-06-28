package com.humanup.matrix.aop.dto;

public class SkillException extends HttpException {
  public SkillException(String message) {
    super(message);
  }

  public SkillException() {
    super();
  }

  @Override
  public String getMessage() {
    return "Cannot Create Skill";
  }
}
