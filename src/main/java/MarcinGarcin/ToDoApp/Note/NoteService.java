package MarcinGarcin.ToDoApp.Note;


import MarcinGarcin.ToDoApp.Course.Course;
import MarcinGarcin.ToDoApp.Course.CourseRepository;
import MarcinGarcin.ToDoApp.Course.CourseService;
import MarcinGarcin.ToDoApp.Task.Task;
import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import MarcinGarcin.ToDoApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Note> getNotesForLoggedInUserAndCourse(String course) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);
        Optional<Course> currentCourse = courseRepository.findByCourseName(course);

        return  noteRepository.findByCourseAndUser(currentCourse.get(), loggedInUser.get());
    }

    public void addNote(String courseName,Note note) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);
        Optional<Course> currentCourse = courseRepository.findByCourseName(courseName);

        note.setUser(loggedInUser.get());
        note.setCourse(currentCourse.get());
        noteRepository.save(note);
    }



    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}