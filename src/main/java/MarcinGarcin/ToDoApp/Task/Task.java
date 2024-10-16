package MarcinGarcin.ToDoApp.Task;

import MarcinGarcin.ToDoApp.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;

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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
