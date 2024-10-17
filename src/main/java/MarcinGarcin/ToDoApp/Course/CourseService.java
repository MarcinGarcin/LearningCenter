package MarcinGarcin.ToDoApp.Course;


import MarcinGarcin.ToDoApp.Task.Task;
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

    public List<Course> getCoursesForLoggedUser() {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isPresent()) {
            return courseRepository.findByUserId(loggedInUser.get().getId());
        } else {
            return List.of();
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

}
