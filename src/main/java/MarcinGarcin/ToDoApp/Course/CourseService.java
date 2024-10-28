package MarcinGarcin.ToDoApp.Course;


import MarcinGarcin.ToDoApp.Note.NoteRepository;
import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    public List<Course> getCoursesForLoggedUser() {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            return courseRepository.findByUserId(loggedInUser.get().getId());
        } else {
            return List.of();
        }
    }

    public Optional<Course> findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }


    public void addCourse(Course course) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            course.setUser(loggedInUser.orElse(null));
            courseRepository.save(course);
        }
    }


    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    //todo poprawić usuwanie kursów
//    public void deleteById(Long id) {
//        if(noteRepository.findByCourseAndUser(courseRepository.findById(id).get(),userRepository.findByUsername(getLoggedInUsername()).get()).isEmpty()){
//        }
//        else{
//            noteRepository.deleteByCourse(courseRepository.findById(id).get());
//        }
//        courseRepository.deleteById(id);
//
//
//
//
//
//
//    }


}
