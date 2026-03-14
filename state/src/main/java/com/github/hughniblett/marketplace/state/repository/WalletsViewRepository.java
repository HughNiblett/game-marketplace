package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.id.WalletsId;
import com.github.hughniblett.marketplace.state.entity.view.WalletsView;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WalletsViewRepository extends CrudRepository<WalletsView, WalletsId> {
  List<WalletsView> findByWalletsIdUserId(Long userId);
}
