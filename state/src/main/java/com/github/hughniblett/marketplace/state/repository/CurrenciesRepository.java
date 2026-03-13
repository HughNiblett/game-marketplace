package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import org.springframework.data.repository.CrudRepository;

public interface CurrenciesRepository extends CrudRepository<Currencies, Long> {
  Currencies findById(long id);
}
