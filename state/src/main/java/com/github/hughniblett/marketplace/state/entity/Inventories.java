package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Inventories {
  @EmbeddedId
  private InventoriesKey walletId;

  private int quantity;
  private int reserved;

  public Inventories(long userId, long itemId, int quantity, int reserved) {
    this.walletId = new InventoriesKey(userId, itemId);
    this.quantity = quantity;
    this.reserved = reserved;
  }
}
