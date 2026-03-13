package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private String id;
  private String username;
  private String passwordHash;
  private String email;
  private OffsetDateTime createdAt;

  public User(String username, String passwordHash, String email) {
    this.username = username;
    this.passwordHash = passwordHash;
    this.email = email;
    this.createdAt = OffsetDateTime.now();
  }
}
