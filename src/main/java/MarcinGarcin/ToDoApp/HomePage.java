package MarcinGarcin.ToDoApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

    @GetMapping("/home")
    public String CourseController() {
        return "home";
    }
}
