package com.spring.springpractice.repository;

import com.spring.springpractice.domain.Todo;
import java.util.List;

public interface TodoRepository {

  Todo setTodo(Todo todo, Long id, String password);

  List<Todo> getTodoByUserID(Long id);

}
