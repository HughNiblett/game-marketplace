package com.github.hughniblett.marketplace.state.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.hughniblett.marketplace.state.entity.Items;
import com.github.hughniblett.marketplace.state.entity.Users;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  private Users user1;
  private Users user2;

  @BeforeEach
  void setUp() {
    user1 = new Users("johnnysins69", "definitely a hashed password", "johnathonsinnerson@sex.com");
    user2 = new Users("fortnite", "another hashed password", "fortnite@epic.com");
    userRepository.deleteAll();
  }

  @Test
  public void testSaveUser() {
    Users savedUser = userRepository.save(user1);
    assertNotNull(savedUser);
    assertEquals(savedUser.getUsername(), user1.getUsername());
  }

  @Test
  public void testFindAllUsers() {
    userRepository.saveAll(List.of(user1, user2));
    List<Users> allUsers = (List<Users>) userRepository.findAll();
    assertNotNull(allUsers);
    assertEquals(2, allUsers.size());
  }
}
