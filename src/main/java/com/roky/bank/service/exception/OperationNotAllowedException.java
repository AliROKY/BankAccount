package com.roky.bank.service.exception;

public class OperationNotAllowedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public OperationNotAllowedException(String message) {
    super(message);
  }

}
