package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldSaveUserAndReturnSavedUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user.getId(), savedUser.getId());
    }

    @Test
    public void shouldSaveUsersAndReturnSavedUsers() {
        User user1 = new User();
        user1.setUsername("test123");
        user1.setPassword("test123");

        User user2 = new User();
        user2.setUsername("test321");
        user2.setPassword("test321");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void shouldFindUserById() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);

        Optional<User> savedUser = userRepository.findById(user.getId());
        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void shouldNotReturnAnyUser() {
        List<User> savedUser = userRepository.findAll();
        Assertions.assertTrue(savedUser.isEmpty());
    }

}
