package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.id.InventoriesId;
import com.github.hughniblett.marketplace.state.entity.view.InventoriesView;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface InventoriesViewRepository extends CrudRepository<InventoriesView, InventoriesId> {
  List<InventoriesView> findByInventoriesIdUserId(Long userId);
}
