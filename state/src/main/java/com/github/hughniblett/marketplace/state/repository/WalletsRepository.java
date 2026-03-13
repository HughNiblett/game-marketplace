package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.Wallets;
import com.github.hughniblett.marketplace.state.entity.WalletsId;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WalletsRepository extends CrudRepository<Wallets, WalletsId> {
  List<Wallets> findByWalletsIdUserId(Long userId);
}
