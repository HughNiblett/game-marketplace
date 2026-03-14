package com.github.hughniblett.marketplace.state.entity.view;

import com.github.hughniblett.marketplace.state.entity.id.InventoriesId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable // Hibernate-specific: marks entity as read-only
@Getter
@NoArgsConstructor
public class InventoriesView {

  @EmbeddedId
  private InventoriesId inventoriesId;
  private String name;
  private int quantity;
  private Integer reserved;

  public InventoriesView(InventoriesId inventoriesId, String name, int quantity, Integer reserved) {
    this.inventoriesId = inventoriesId;
    this.name = name;
    this.quantity = quantity;
    this.reserved = reserved;
  }

  @Override
  public String toString() {
    return String.format("InventoriesView [userId=%d, itemId=%d, name=%s, quantity=%d, reserved=%d]", inventoriesId.getUserId(), inventoriesId.getItemId(), name, quantity, reserved);
  }
}