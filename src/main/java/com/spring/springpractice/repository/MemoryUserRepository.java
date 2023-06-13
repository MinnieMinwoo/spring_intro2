package com.spring.springpractice.repository;

import com.spring.springpractice.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

  private static Map<Long, User> userStoreMap = new HashMap<>();
  private static long nextID = 0L;

  @Override
  public User signUp(User user) {
    if (findByName(user.getName()).isPresent()) {
      throw new IllegalStateException("중복된 이름입니다.");
    }
    user.setId(++nextID);
    userStoreMap.put(user.getId(), user);
    return user;
  }

  @Override
  public Optional<User> findByID(Long id) {
    return Optional.ofNullable(userStoreMap.get(id));
  }

  @Override
  public Optional<User> findByName(String name) {
    for (User data : userStoreMap.values()) {
      if (data.getName().equals(name)) {
        return Optional.ofNullable(data);
      }
    }

    return Optional.empty();
  }

  @Override
  public List<User> findAll() {
    return new ArrayList<>(userStoreMap.values());
  }

  public void clearStore() {
    userStoreMap.clear();
  }
}
