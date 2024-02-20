package com.betrybe.agrix.services.exceptions;

/**
 * Class.
 */
public class FertilizerNotFoundException extends RuntimeException {

  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}