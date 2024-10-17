package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;



@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_id")
    private User user;

    private String name;

    private String priority;

    private LocalDate dueDate;

    public Task() {
    }

    public Task(User user, String name, String priority, LocalDate dueDate) {
        this.user = user;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}
