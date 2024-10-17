package MarcinGarcin.ToDoApp.Course;

import MarcinGarcin.ToDoApp.Task.Task;
import MarcinGarcin.ToDoApp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByUserId(Long userId);


}
