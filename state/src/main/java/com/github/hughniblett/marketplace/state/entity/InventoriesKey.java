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
public class InventoriesKey implements Serializable {
  private Long userId;
  private Long itemId;

  public InventoriesKey(Long userId, Long itemId) {
    this.userId = userId;
    this.itemId = itemId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof InventoriesKey)) return false;
    InventoriesKey that = (InventoriesKey) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(itemId, that.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, itemId);
  }
}