package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

  @Id
  @GeneratedValue
  private String id;
  private String name;
  private String description;

  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
