package com.spring.springpractice.repository;

import com.spring.springpractice.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

  User signUp(User user);

  Optional<User> findByID(Long id);

  Optional<User> findByName(String id);

  List<User> findAll();
}
