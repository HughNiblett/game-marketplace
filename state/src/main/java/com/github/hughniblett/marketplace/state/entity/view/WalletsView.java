package com.github.hughniblett.marketplace.state.entity.view;

import com.github.hughniblett.marketplace.state.entity.id.WalletsId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Getter
@NoArgsConstructor
public class WalletsView {

  @EmbeddedId
  private WalletsId walletsId;
  private String name;
  private int amount;

  public WalletsView(WalletsId walletsId, String name, int amount) {
    this.walletsId = walletsId;
    this.name = name;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return String.format("WalletsView [userId=%d, currencyId=%d, name=%s, amount=%d]", walletsId.getUserId(), walletsId.getCurrencyId(), name, amount);
  }
}