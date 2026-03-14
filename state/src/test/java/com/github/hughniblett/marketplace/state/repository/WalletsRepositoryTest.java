package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import com.github.hughniblett.marketplace.state.entity.Users;
import com.github.hughniblett.marketplace.state.entity.Wallets;
import com.github.hughniblett.marketplace.state.entity.id.WalletsId;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WalletsRepositoryTest {

  @Autowired
  private WalletsRepository walletsRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CurrenciesRepository currenciesRepository;

  private Users user1;
  private Users user2;
  private Currencies currency1;
  private Currencies currency2;

  @BeforeEach
  void setUp() {
    walletsRepository.deleteAll();
    usersRepository.deleteAll();
    currenciesRepository.deleteAll();

    user1 = usersRepository.save(new Users("player1", "hashed_pw", "player1@game.com"));
    user2 = usersRepository.save(new Users("player2", "hashed_pw", "player2@game.com"));
    currency1 = currenciesRepository.save(new Currencies("Gold"));
    currency2 = currenciesRepository.save(new Currencies("Silver"));
  }

  @Test
  public void testSaveWallet() {
    Wallets wallet = new Wallets(user1.getId(), currency1.getId(), 100);
    Wallets saved = walletsRepository.save(wallet);
    assertNotNull(saved);
    assertEquals(100, saved.getAmount());
    assertEquals(user1.getId(), saved.getWalletsId().getUserId());
    assertEquals(currency1.getId(), saved.getWalletsId().getCurrencyId());
  }

  @Test
  public void testFindAllWallets() {
    walletsRepository.save(new Wallets(user1.getId(), currency1.getId(), 100));
    walletsRepository.save(new Wallets(user1.getId(), currency2.getId(), 50));
    List<Wallets> all = (List<Wallets>) walletsRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindByWalletsIdUserId() {
    walletsRepository.save(new Wallets(user1.getId(), currency1.getId(), 100));
    walletsRepository.save(new Wallets(user1.getId(), currency2.getId(), 50));
    List<Wallets> userWallets = walletsRepository.findByWalletsIdUserId(user1.getId());
    assertNotNull(userWallets);
    assertEquals(2, userWallets.size());
    assertTrue(userWallets.stream().allMatch(w -> w.getWalletsId().getUserId().equals(user1.getId())));
  }

  @Test
  public void testFindById() {
    walletsRepository.save(new Wallets(user1.getId(), currency1.getId(), 200));
    WalletsId id = new WalletsId(user1.getId(), currency1.getId());
    assertTrue(walletsRepository.findById(id).isPresent());
    assertEquals(200, walletsRepository.findById(id).get().getAmount());
  }

  @Test
  public void testDeleteWallet() {
    Wallets saved = walletsRepository.save(new Wallets(user1.getId(), currency1.getId(), 100));
    walletsRepository.delete(saved);
    assertFalse(walletsRepository.existsById(saved.getWalletsId()));
  }

  @Test
  public void testDeleteByCurrencyId() {
    walletsRepository.save(new Wallets(user1.getId(), currency1.getId(), 100));
    walletsRepository.save(new Wallets(user1.getId(), currency2.getId(), 50));
    walletsRepository.save(new Wallets(user2.getId(), currency1.getId(), 200));
    walletsRepository.save(new Wallets(user2.getId(), currency2.getId(), 30));
    long amountDeleted = walletsRepository.deleteByWalletsIdCurrencyId(currency1.getId());
    assertEquals(2, amountDeleted);
  }
}

