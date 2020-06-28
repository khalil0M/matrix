package com.humanup.matrix.aop.dto;

public class TypeSkillException extends HttpException {
  public TypeSkillException(String message) {
    super(message);
  }

  public TypeSkillException() {
    super();
  }

  @Override
  public String getMessage() {
    return "Cannot Create TypeSkill";
  }
}
