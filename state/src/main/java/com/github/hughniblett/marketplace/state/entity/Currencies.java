package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Currencies {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  public Currencies(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Currencies [id=%d, name=%s]", id, name);
  }
}
