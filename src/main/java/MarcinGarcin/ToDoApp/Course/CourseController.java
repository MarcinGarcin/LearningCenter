package MarcinGarcin.ToDoApp.Course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {


    @GetMapping("/course")
    public String CourseController() {
        return "course";
    }
}
