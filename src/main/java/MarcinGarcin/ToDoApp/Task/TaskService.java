package MarcinGarcin.ToDoApp.Task;


import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasksForLoggedInUser() {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            return taskRepository.findByUserId(loggedInUser.get().getId());
        } else {
            return List.of();
        }
    }

    public void addTask(Task task) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            task.setUser(loggedInUser.orElse(null));
            taskRepository.save(task);
        }
    }

    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}


