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
public class WalletsKey implements Serializable {
  private Long userId;
  private Long currencyId;

  public WalletsKey(Long userId, Long currencyId) {
    this.userId = userId;
    this.currencyId = currencyId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WalletsKey)) return false;
    WalletsKey that = (WalletsKey) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(currencyId, that.currencyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, currencyId);
  }
}