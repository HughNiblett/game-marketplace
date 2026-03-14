package com.github.hughniblett.marketplace.state.entity;

import com.github.hughniblett.marketplace.state.entity.id.WalletsId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Wallets {
  @EmbeddedId
  private WalletsId walletsId;

  private int amount;

  public Wallets(long userId, long currencyId, int amount) {
    this.walletsId = new WalletsId(userId, currencyId);
    this.amount = amount;
  }
}
