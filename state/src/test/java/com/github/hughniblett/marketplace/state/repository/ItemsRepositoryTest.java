package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Items;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemsRepositoryTest {

  @Autowired
  private ItemsRepository itemsRepository;

  private Items item1;
  private Items item2;

  @BeforeEach
  void setUp() {
    item1 = new Items("Sword", "A sharp blade");
    item2 = new Items("Shield", "A sturdy shield");
    itemsRepository.deleteAll();
  }

  @Test
  public void testSaveItem() {
    Items saved = itemsRepository.save(item1);
    assertNotNull(saved);
    assertTrue(saved.getId() > 0);
    assertEquals("Sword", saved.getName());
    assertEquals("A sharp blade", saved.getDescription());
  }

  @Test
  public void testFindAllItems() {
    itemsRepository.saveAll(List.of(item1, item2));
    List<Items> all = (List<Items>) itemsRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindById() {
    Items saved = itemsRepository.save(item1);
    Items found = itemsRepository.findById(saved.getId());
    assertNotNull(found);
    assertEquals(saved.getId(), found.getId());
    assertEquals("Sword", found.getName());
  }

  @Test
  public void testDeleteItem() {
    Items saved = itemsRepository.save(item1);
    itemsRepository.delete(saved);
    assertFalse(itemsRepository.existsById(saved.getId()));
  }
}

