package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CurrenciesRepositoryTest {

  @Autowired
  private CurrenciesRepository currenciesRepository;

  private Currencies currency1;
  private Currencies currency2;

  @BeforeEach
  void setUp() {
    currency1 = new Currencies("Gold");
    currency2 = new Currencies("Silver");
    currenciesRepository.deleteAll();
  }

  @Test
  public void testSaveCurrency() {
    Currencies saved = currenciesRepository.save(currency1);
    assertNotNull(saved);
    assertTrue(saved.getId() > 0);
    assertEquals("Gold", saved.getName());
  }

  @Test
  public void testFindAllCurrencies() {
    currenciesRepository.saveAll(List.of(currency1, currency2));
    List<Currencies> all = (List<Currencies>) currenciesRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindById() {
    Currencies saved = currenciesRepository.save(currency1);
    Currencies found = currenciesRepository.findById(saved.getId());
    assertNotNull(found);
    assertEquals(saved.getId(), found.getId());
    assertEquals("Gold", found.getName());
  }

  @Test
  public void testDeleteCurrency() {
    Currencies saved = currenciesRepository.save(currency1);
    currenciesRepository.delete(saved);
    assertFalse(currenciesRepository.existsById(saved.getId()));
  }
}

