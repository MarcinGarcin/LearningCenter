package MarcinGarcin.ToDoApp.Note;


import MarcinGarcin.ToDoApp.Course.Course;
import MarcinGarcin.ToDoApp.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;

    @Column(length = 65535)
    private String content;




}
