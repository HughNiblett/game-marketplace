package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
