package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Inventories;
import com.github.hughniblett.marketplace.state.entity.Items;
import com.github.hughniblett.marketplace.state.entity.Users;
import com.github.hughniblett.marketplace.state.entity.id.InventoriesId;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventoriesRepositoryTest {

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

    user = usersRepository.save(new Users("player1", "hashed_pw", "player1@game.com"));
    item1 = itemsRepository.save(new Items("Sword", "A sharp blade"));
    item2 = itemsRepository.save(new Items("Shield", "A sturdy shield"));
  }

  @Test
  public void testSaveInventory() {
    Inventories inventory = new Inventories(user.getId(), item1.getId(), 3, 0);
    Inventories saved = inventoriesRepository.save(inventory);
    assertNotNull(saved);
    assertEquals(3, saved.getQuantity());
    assertEquals(0, saved.getReserved());
    assertEquals(user.getId(), saved.getInventoriesId().getUserId());
    assertEquals(item1.getId(), saved.getInventoriesId().getItemId());
  }

  @Test
  public void testFindAllInventories() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 3, 0));
    inventoriesRepository.save(new Inventories(user.getId(), item2.getId(), 1, 0));
    List<Inventories> all = (List<Inventories>) inventoriesRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindByInventoriesIdUserId() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 3, 0));
    inventoriesRepository.save(new Inventories(user.getId(), item2.getId(), 1, 0));
    List<Inventories> userInventory = inventoriesRepository.findByInventoriesIdUserId(user.getId());
    assertNotNull(userInventory);
    assertEquals(2, userInventory.size());
    assertTrue(userInventory.stream().allMatch(i -> i.getInventoriesId().getUserId().equals(user.getId())));
  }

  @Test
  public void testFindById() {
    inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 5, 1));
    InventoriesId id = new InventoriesId(user.getId(), item1.getId());
    assertTrue(inventoriesRepository.findById(id).isPresent());
    assertEquals(5, inventoriesRepository.findById(id).get().getQuantity());
    assertEquals(1, inventoriesRepository.findById(id).get().getReserved());
  }

  @Test
  public void testDeleteInventory() {
    Inventories saved = inventoriesRepository.save(new Inventories(user.getId(), item1.getId(), 3, 0));
    inventoriesRepository.delete(saved);
    assertFalse(inventoriesRepository.existsById(saved.getInventoriesId()));
  }
}

