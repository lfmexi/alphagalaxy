package org.lfmexi.alphagalaxy.repositories.exceptions;

public class DuplicatedIdException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DuplicatedIdException(String s) {
    super(s);
  }

}
