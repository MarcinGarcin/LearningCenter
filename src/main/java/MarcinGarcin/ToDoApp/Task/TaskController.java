package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TaskController {

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/tasks")
    public String showTasks() {
        return "Tutaj beda taski";
    }


}
