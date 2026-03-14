package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Inventories;
import com.github.hughniblett.marketplace.state.entity.Items;
import com.github.hughniblett.marketplace.state.entity.Users;
import com.github.hughniblett.marketplace.state.entity.id.InventoriesId;
import com.github.hughniblett.marketplace.state.entity.view.InventoriesView;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventoriesViewRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private InventoriesViewRepository inventoriesViewRepository;

  @Autowired
  private InventoriesRepository inventoriesRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private ItemsRepository itemsRepository;

  private Users user;
  private Items item1;
  private Items item2;

  @BeforeEach
  void setUp() {
    inventoriesRepository.deleteAll();
    usersRepository.deleteAll();
    itemsRepository.deleteAll();
    entityManager.flush();
    entityManager.clear();

    user = usersRepository.save(new Users("player1", "hashed_pw", "player1@game.com"));
    item1 = itemsRepository.save(new Items("Sword", "A sharp blade"));
    item2 = itemsRepository.save(new Items("Shield", "A sturdy shield"));
    entityManager.flush();
    entityManager.clear();
  }

  @Test
  public void testFindAllInventoriesView() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 3, 0));
    inventoriesRepository.save(new Inventories(user.getId(), item2.getId(), 1, 0));
    entityManager.flush();
    entityManager.clear();
    List<InventoriesView> all = (List<InventoriesView>) inventoriesViewRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindByInventoriesIdUserId() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 3, 0));
    inventoriesRepository.save(new Inventories(user.getId(), item2.getId(), 1, 0));
    entityManager.flush();
    entityManager.clear();
    List<InventoriesView> userInventory = inventoriesViewRepository.findByInventoriesIdUserId(user.getId());
    assertNotNull(userInventory);
    assertEquals(2, userInventory.size());
    assertTrue(userInventory.stream().allMatch(i -> i.getInventoriesId().getUserId().equals(user.getId())));
  }

  @Test
  public void testFindByInventoriesIdUserId_includesItemName() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 5, 1));
    entityManager.flush();
    entityManager.clear();
    List<InventoriesView> userInventory = inventoriesViewRepository.findByInventoriesIdUserId(user.getId());
    assertEquals(1, userInventory.size());
    assertEquals("Sword", userInventory.getFirst().getName());
    assertEquals(5, userInventory.getFirst().getQuantity());
    assertEquals(1, userInventory.getFirst().getReserved());
  }

  @Test
  public void testFindById() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 5, 2));
    entityManager.flush();
    entityManager.clear();
    InventoriesId id = new InventoriesId(user.getId(), item1.getId());
    Optional<InventoriesView> result = inventoriesViewRepository.findById(id);
    assertTrue(result.isPresent());
    assertEquals(5, result.get().getQuantity());
    assertEquals(2, result.get().getReserved());
    assertEquals("Sword", result.get().getName());
  }

  @Test
  public void testFindByInventoriesIdUserId_emptyWhenNoInventory() {
    List<InventoriesView> userInventory = inventoriesViewRepository.findByInventoriesIdUserId(user.getId());
    assertNotNull(userInventory);
    assertTrue(userInventory.isEmpty());
  }
}

