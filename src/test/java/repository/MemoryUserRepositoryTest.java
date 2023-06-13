package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.spring.springpractice.domain.User;
import com.spring.springpractice.repository.MemoryUserRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryUserRepositoryTest {

  MemoryUserRepository repository = new MemoryUserRepository();

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void 계정저장() {
    User user1 = new User();
    user1.setName("user1");
    user1.setPassword("password1");
    repository.signUp(user1);

    User result = repository.findByName(user1.getName()).get();
    assertThat(result).isEqualTo(user1);
  }

  @Test
  public void 계정저장_이름중복() {
    User user1 = new User();
    user1.setName("user1");
    user1.setPassword("password1");
    repository.signUp(user1);

    User user2 = new User();
    user2.setName("user1"); // 고의 중복
    user2.setPassword("password2");
    IllegalStateException error = assertThrows(IllegalStateException.class, () -> repository.signUp(user2));
    assertThat(error.getMessage()).isEqualTo("중복된 이름입니다.");
  }

  @Test
  public void 계정찾기_이름() {
    User user1 = new User();
    user1.setName("user1");
    user1.setPassword("password1");
    repository.signUp(user1);

    User user2 = new User();
    user2.setName("user2");
    user2.setPassword("password2");
    repository.signUp(user2);

    User queryResult = repository.findByName("user1").get();
    assertThat(queryResult).isEqualTo(user1);
  }

  @Test
  public void 계정찾기_전체() {
    User user1 = new User();
    user1.setName("user1");
    user1.setPassword("password1");
    repository.signUp(user1);

    User user2 = new User();
    user2.setName("user2");
    user2.setPassword("password2");
    repository.signUp(user2);

    List<User> queryResult = repository.findAll();
    assertThat(queryResult.size()).isEqualTo(2);
  }
}
