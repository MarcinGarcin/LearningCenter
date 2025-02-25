package MarcinGarcin.ToDoApp.Course;

import MarcinGarcin.ToDoApp.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String courseName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
