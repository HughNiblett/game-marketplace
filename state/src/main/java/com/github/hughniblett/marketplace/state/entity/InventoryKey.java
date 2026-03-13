package com.github.hughniblett.marketplace.state.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class InventoryKey implements Serializable {
  private Integer userId;
  private Integer itemId;

  public InventoryKey(Integer userId, Integer itemId) {
    this.userId = userId;
    this.itemId = itemId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof InventoryKey)) return false;
    InventoryKey that = (InventoryKey) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(itemId, that.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, itemId);
  }
}