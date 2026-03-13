package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Wallet {
  @EmbeddedId
  private WalletKey walletId;

  private int amount;

  public Wallet(int userId, int currencyId, int amount) {
    this.walletId = new WalletKey(userId, currencyId);
    this.amount = amount;
  }
}
