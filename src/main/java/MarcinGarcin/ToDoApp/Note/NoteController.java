package MarcinGarcin.ToDoApp.Note;

import MarcinGarcin.ToDoApp.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/course/{courseName}")
    public String getNotesForCourse(@PathVariable String courseName, Model model) {
        List<Note> notes = noteService.getNotesForLoggedInUserAndCourse(courseName);
        model.addAttribute("notes", notes);
        model.addAttribute("courseName", courseName);
        return "note";
    }


    @GetMapping("/course/{courseName}/new")
    public String showCreateNoteForm(@PathVariable String courseName, Model model) {
        model.addAttribute("courseName", courseName);
        model.addAttribute("note", new Note());
        return "newNote";
    }


    @PostMapping("/course/{courseName}/new")
    public String createNote(@PathVariable String courseName, @ModelAttribute Note note) {
        noteService.addNote(courseName, note);
        return "redirect:/course/" + courseName;
    }
}
