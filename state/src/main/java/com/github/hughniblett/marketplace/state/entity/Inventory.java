package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Inventory {
  @EmbeddedId
  private InventoryKey walletId;

  private int quantity;
  private int reserved;

  public Inventory(int userId, int itemId, int quantity, int reserved) {
    this.walletId = new InventoryKey(userId, itemId);
    this.quantity = quantity;
    this.reserved = reserved;
  }
}
