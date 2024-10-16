package MarcinGarcin.ToDoApp.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskService taskService;


    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasksForLoggedInUser();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @GetMapping("/new")
    public String showCreateTaskForm() {
        return "newTask";
    }


    @PostMapping("/new")
    public String createTask(@ModelAttribute Task task) {


        taskService.addTask(task);
        return "redirect:/task";
    }
}

