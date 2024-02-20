package com.betrybe.agrix.services.exceptions;

/**
 * Classe.
 */
public class FarmNotFoundException extends ClassNotFoundException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
