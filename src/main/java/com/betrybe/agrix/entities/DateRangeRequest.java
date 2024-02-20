package com.betrybe.agrix.entities;

import java.time.LocalDate;

/**
 * Classe.
 */
public class DateRangeRequest {

  private LocalDate start;
  private LocalDate end;

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }


}