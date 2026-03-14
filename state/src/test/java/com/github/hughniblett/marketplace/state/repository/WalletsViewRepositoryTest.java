package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import com.github.hughniblett.marketplace.state.entity.Users;
import com.github.hughniblett.marketplace.state.entity.Wallets;
import com.github.hughniblett.marketplace.state.entity.id.WalletsId;
import com.github.hughniblett.marketplace.state.entity.view.WalletsView;
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
public class WalletsViewRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private WalletsViewRepository walletsViewRepository;

  @Autowired
  private WalletsRepository walletsRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CurrenciesRepository currenciesRepository;

  private Users user;
  private Currencies currency1;
  private Currencies currency2;

  @BeforeEach
  void setUp() {
    walletsRepository.deleteAll();
    usersRepository.deleteAll();
    currenciesRepository.deleteAll();
    entityManager.flush();
    entityManager.clear();

    user = usersRepository.save(new Users("player1", "hashed_pw", "player1@game.com"));
    currency1 = currenciesRepository.save(new Currencies("Gold"));
    currency2 = currenciesRepository.save(new Currencies("Silver"));
    entityManager.flush();
    entityManager.clear();
  }

  @Test
  public void testFindAllWalletsView() {
    walletsRepository.save(new Wallets(user.getId(), currency1.getId(), 100));
    walletsRepository.save(new Wallets(user.getId(), currency2.getId(), 50));
    entityManager.flush();
    entityManager.clear();
    List<WalletsView> all = (List<WalletsView>) walletsViewRepository.findAll();
    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  public void testFindByWalletsIdUserId() {
    walletsRepository.save(new Wallets(user.getId(), currency1.getId(), 100));
    walletsRepository.save(new Wallets(user.getId(), currency2.getId(), 50));
    entityManager.flush();
    entityManager.clear();
    List<WalletsView> userWallets = walletsViewRepository.findByWalletsIdUserId(user.getId());
    assertNotNull(userWallets);
    assertEquals(2, userWallets.size());
    assertTrue(userWallets.stream().allMatch(w -> w.getWalletsId().getUserId().equals(user.getId())));
  }

  @Test
  public void testFindByWalletsIdUserId_includesCurrencyName() {
    walletsRepository.save(new Wallets(user.getId(), currency1.getId(), 100));
    entityManager.flush();
    List<WalletsView> userWallets = walletsViewRepository.findByWalletsIdUserId(user.getId());
    assertEquals(1, userWallets.size());
    assertEquals("Gold", userWallets.getFirst().getName());
    assertEquals(100, userWallets.getFirst().getAmount());
  }

  @Test
  public void testFindById() {
    walletsRepository.save(new Wallets(user.getId(), currency1.getId(), 200));
    entityManager.flush();
    entityManager.clear();
    WalletsId id = new WalletsId(user.getId(), currency1.getId());
    Optional<WalletsView> result = walletsViewRepository.findById(id);
    assertTrue(result.isPresent());
    assertEquals(200, result.get().getAmount());
    assertEquals("Gold", result.get().getName());
  }

  @Test
  public void testFindByWalletsIdUserId_emptyWhenNoWallets() {
    List<WalletsView> userWallets = walletsViewRepository.findByWalletsIdUserId(user.getId());
    assertNotNull(userWallets);
    assertTrue(userWallets.isEmpty());
  }
}

