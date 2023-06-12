package com.spring.springpractice.repository;

import com.spring.springpractice.domain.Todo;
import com.spring.springpractice.domain.User;

import java.util.List;

public interface TodoRepository {
    Todo setTodo(Todo todo, User user);
    List<Todo> getTodoById(String id);

}
