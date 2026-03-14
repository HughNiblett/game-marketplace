package com.github.hughniblett.marketplace.state.repository;

import com.github.hughniblett.marketplace.state.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
  Users findById(long id);
}
