package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.Inventories;
import com.github.hughniblett.marketplace.state.entity.InventoriesId;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface InventoriesRepository extends CrudRepository<Inventories, InventoriesId> {
  List<Inventories> findByInventoriesIdUserId(Long userId);
}
