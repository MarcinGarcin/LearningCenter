package MarcinGarcin.ToDoApp.Task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;


    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }
}
