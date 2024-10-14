package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class CreateTaskController {

    @Autowired
    private TaskRepository taskRepository;

    //todo zmienic autowired na odpowiednia annotacje
    @Autowired
    private User user;



    @PostMapping(value = "/req/newTask", consumes = "application/json")
    public Task createTask(@RequestBody Task task) {
        task.setUserId(user.getId());
        return taskRepository.save(task);
    }


}
