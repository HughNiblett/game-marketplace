package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Wallets {
  @EmbeddedId
  private WalletsKey walletId;

  private int amount;

  public Wallets(long userId, long currencyId, int amount) {
    this.walletId = new WalletsKey(userId, currencyId);
    this.amount = amount;
  }
}
