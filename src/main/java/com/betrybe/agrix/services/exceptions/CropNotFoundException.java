package com.betrybe.agrix.services.exceptions;

/**
 * Classe.
 */
public class CropNotFoundException extends ClassNotFoundException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
