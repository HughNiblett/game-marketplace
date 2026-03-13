package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Items, Long> {
  Items findById(long id);
}
