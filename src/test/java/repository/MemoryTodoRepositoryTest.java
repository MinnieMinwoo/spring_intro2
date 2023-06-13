package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.spring.springpractice.domain.Todo;
import com.spring.springpractice.domain.User;
import com.spring.springpractice.repository.MemoryTodoRepository;
import com.spring.springpractice.repository.MemoryUserRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemoryTodoRepositoryTest {

  MemoryTodoRepository todoRepository = new MemoryTodoRepository();
  MemoryUserRepository userRepository = new MemoryUserRepository();

  @BeforeEach
  public void beforeEach() {
    User user = new User();
    user.setName("user");
    user.setPassword("password1");
    userRepository.signUp(user);
  }

  @AfterEach
  public void afterEach() {
    userRepository.clearStore();
    todoRepository.clearSore();
  }

  @Test
  public void 할일등록() {
    User user = userRepository.findByName("user").get();
    Todo todo = new Todo();
    todo.setTitle("할일1");
    todoRepository.setTodo(todo, user.getId(), user.getPassword());

    List<Todo> result = todoRepository.getTodoByUserID(user.getId());
    assertThat(result.get(0)).isEqualTo(todo);
  }

  @Test
  public void 할일등록_예외_유저없음() {
    Todo todo = new Todo();
    todo.setTitle("할일1");

    IllegalStateException error = assertThrows(IllegalStateException.class,
        () -> todoRepository.setTodo(todo, 12345L, "12345"));
    assertThat(error.getMessage()).isEqualTo("해당 유저가 존재하지 않습니다.");
  }

  @Test
  public void 할일등록_예외_비밀번호틀림() {
    User user = userRepository.findByName("user").get();
    Todo todo = new Todo();
    todo.setTitle("할일1");

    IllegalStateException error = assertThrows(IllegalStateException.class,
        () -> todoRepository.setTodo(todo, user.getId(), "12345"));
    assertThat(error.getMessage()).isEqualTo("비밀번호가 잘못되었습니다.");
  }

  @Test
  public void 할일가져오기() {
    User user = userRepository.findByName("user").get();
    Todo todo1 = new Todo();
    todo1.setTitle("할일1");
    Todo todo2 = new Todo();
    todo2.setTitle("할일2");
    todoRepository.setTodo(todo1, user.getId(), user.getPassword());
    todoRepository.setTodo(todo2, user.getId(), user.getPassword());

    List<Todo> queryResult = todoRepository.getTodoByUserID(user.getId());
    assertThat(queryResult.size()).isEqualTo(2);
  }
}
