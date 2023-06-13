package com.spring.springpractice.repository;

import com.spring.springpractice.domain.Todo;
import com.spring.springpractice.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MemoryTodoRepository implements TodoRepository {

  private static Map<Long, Todo> todoStoreMap = new HashMap<>();
  private static Long nextID = 0L;

  @Override
  public Todo setTodo(Todo todo, Long id, String password) {
    MemoryUserRepository userRepository = new MemoryUserRepository();

    Optional<User> userResult = userRepository.findByID(id);
    if (userResult.isEmpty()) {
      throw new IllegalStateException("해당 유저가 존재하지 않습니다.");
    }

    User user = userResult.get();
    if (!Objects.equals(user.getPassword(), password)) {
      throw new IllegalStateException("비밀번호가 잘못되었습니다.");
    }

    todo.setTodoID(++nextID);
    todo.setUserID(id);
    todoStoreMap.put(todo.getTodoID(), todo);
    return todo;
  }

  @Override
  public List<Todo> getTodoByUserID(Long id) {
    List<Todo> queryResult = new ArrayList<>();
    todoStoreMap.forEach((key, value) -> {
      if (value.getUserID() == id) {
        queryResult.add(value);
      }
    });
    return queryResult;
  }

  public void clearSore() {
    todoStoreMap.clear();
  }
}
