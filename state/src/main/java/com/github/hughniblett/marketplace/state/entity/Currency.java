package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Currency {

  @Id
  @GeneratedValue
  private String id;
  private String name;

  public Currency() {}

  public Currency(String name) {
    this.name = name;
  }
}
