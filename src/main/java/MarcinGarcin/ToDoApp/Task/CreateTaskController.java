package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/req")
public class CreateTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/req/newTask")
    public String showTaskForm() {
        return "newTask";
    }

    @RequestMapping("/newTask")
    public ResponseEntity<String> createTask(@RequestBody Task task, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        task.setUserId(userId);

        taskRepository.save(task);

        return ResponseEntity.ok("Task created successfully");
    }
}


