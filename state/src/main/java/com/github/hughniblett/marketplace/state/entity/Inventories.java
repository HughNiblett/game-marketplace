package com.github.hughniblett.marketplace.state.entity;

import com.github.hughniblett.marketplace.state.entity.id.InventoriesId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Inventories {
  @EmbeddedId
  private InventoriesId inventoriesId;

  private int quantity;
  private int reserved;

  public Inventories(long userId, long itemId, int quantity, int reserved) {
    this.inventoriesId = new InventoriesId(userId, itemId);
    this.quantity = quantity;
    this.reserved = reserved;
  }
}
