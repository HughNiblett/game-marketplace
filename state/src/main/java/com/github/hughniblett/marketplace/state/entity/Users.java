package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String passwordHash;
  private String email;
  private OffsetDateTime createdAt;

  public Users(String username, String passwordHash, String email) {
    this.username = username;
    this.passwordHash = passwordHash;
    this.email = email;
    this.createdAt = OffsetDateTime.now();
  }

  @Override
  public String toString() {
    return String.format("User [id=%d, username=%s, passwordHash=%s, email=%s, createdAt=%s]",  id, username, passwordHash, email, createdAt.toString());
  }
}
