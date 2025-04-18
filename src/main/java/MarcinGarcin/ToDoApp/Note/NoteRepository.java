package MarcinGarcin.ToDoApp.Note;

import MarcinGarcin.ToDoApp.Course.Course;
import MarcinGarcin.ToDoApp.User.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCourseAndUser(Course course, User user);
    void deleteByCourse(Course course);
}

