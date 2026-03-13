package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Currency {

  @Id
  @GeneratedValue
  private String id;
  private String name;

  public Currency(String name) {
    this.name = name;
  }
}
