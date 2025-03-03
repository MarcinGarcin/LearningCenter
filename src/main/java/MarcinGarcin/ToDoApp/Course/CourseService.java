package MarcinGarcin.ToDoApp.Course;

import MarcinGarcin.ToDoApp.Note.NoteRepository;
import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, NoteRepository noteRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public List<Course> getCoursesForLoggedUser() {
        return userRepository.findByUsername(getLoggedInUsername())
                .map(user -> courseRepository.findByUserId(user.getId()))
                .orElse(List.of());
    }

    public void addCourse(Course course) {
        if (course.getCourseName().contains(" ")) {
            course.setCourseName(course.getCourseName().replace(" ", "_"));
        }
        userRepository.findByUsername(getLoggedInUsername()).ifPresent(course::setUser);
        courseRepository.save(course);
    }

    @Transactional
    public void deleteById(Long id) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            Optional<Course> course = courseRepository.findById(id);

            if (course.isPresent()) {
                if (!noteRepository.findByCourseAndUser(course.get(), loggedInUser.get()).isEmpty()) {
                    noteRepository.deleteByCourse(course.get());
                }
                courseRepository.deleteById(id);
            }
        }
    }

    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
    }
}

