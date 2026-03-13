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
public class WalletKey implements Serializable {
  private Integer userId;
  private Integer currencyId;

  public WalletKey(Integer userId, Integer currencyId) {
    this.userId = userId;
    this.currencyId = currencyId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WalletKey)) return false;
    WalletKey that = (WalletKey) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(currencyId, that.currencyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, currencyId);
  }
}