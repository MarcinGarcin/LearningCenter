package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private User user2;

    @BeforeEach
    public void setUp() {
        user = createUser("Test User", "Test Password");
        user2 = createUser("Test User 2", "Test Password 2");
    }

    private User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    private Task createTask(User user, String name) {
        Task task = new Task();
        task.setUser(user);
        task.setName(name);
        return taskRepository.save(task);
    }

    @Test
    public void shouldSaveTaskAndReturnSavedTask() {
        Task task = createTask(user, "Test Task");
        Assertions.assertNotNull(task);
        Assertions.assertEquals("Test Task", task.getName());
        Assertions.assertTrue(task.getId() > 0);
    }

    @Test
    public void shouldReturnMoreThanOneSavedTask() {
        createTask(user, "Test Task1");
        createTask(user, "Test Task2");

        List<Task> tasks = taskRepository.findAll();

        Assertions.assertEquals(2, tasks.size());
        Assertions.assertNotNull(tasks);
    }

    @Test
    public void shouldFindTaskByUserId() {
        createTask(user, "Test Task1");
        createTask(user2, "Test Task2");

        List<Task> savedTasks = taskRepository.findByUserId(user.getId());

        Assertions.assertNotNull(savedTasks);
        Assertions.assertEquals(1, savedTasks.size());
    }
}