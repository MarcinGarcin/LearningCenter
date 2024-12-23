package MarcinGarcin.ToDoApp.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public String getCourse(Model model) {
        List<Course> courses = courseService.getCoursesForLoggedUser();
        model.addAttribute("courses", courses);
        return "course";
    }

    @GetMapping("/new")
    public String showCreateCourseForm() {
        return "newCourse";
    }

    @PostMapping("/new")
    public String createCourse(@ModelAttribute Course course, Model model) {
        List<Course> courses = courseService.getCoursesForLoggedUser();

        for (Course c : courses) {
            if (c.getCourseName().equals(course.getCourseName())) {
                model.addAttribute("errorMessage", "This course already exists.");
                return "newCourse";
            }
        }

        courseService.addCourse(course);
        return "redirect:/course";
    }


    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/course";
    }



}
