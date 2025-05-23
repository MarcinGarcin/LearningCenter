package MarcinGarcin.ToDoApp.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }



    @GetMapping("/course/{courseName}")
    public String getNotesForCourse(@PathVariable String courseName, Model model) {
        List<Note> notes = noteService.getNotesForLoggedInUserAndCourse(courseName);
        model.addAttribute("notes", notes);
        model.addAttribute("courseName", courseName);
        return "notes";
    }

    @GetMapping("/course/{courseName}/{noteId}")
    public String showAlreadyCreatedNote(@PathVariable String courseName,@PathVariable long noteId, Model model) {
            Optional<Note> optionalNote = noteService.getNoteFromId(noteId);

            if (optionalNote.isPresent()) {
                model.addAttribute("note", optionalNote.get());
            } else {
                return "errorPage";
            }

            return "note";
        }

    @PostMapping("/course/{courseName}/{noteId}")
    public String updateNote(@PathVariable String courseName, @PathVariable long noteId, @ModelAttribute Note note, Model model) {
        if(note.getContent().length()>65535){
            model.addAttribute("errorMessage", "Note is too long.");
            return "newNote";
        }
        noteService.updateNote(noteId, note);
        return "redirect:/course/" + courseName + "/" + noteId;
    }

    @PostMapping("/course/{courseName}/delete/{noteId}")
    public String deleteNote(@PathVariable String courseName, @PathVariable long noteId) {
        noteService.deleteNote(courseName,noteId);
        return "redirect:/course/" + courseName;
    }




    @GetMapping("/course/{courseName}/new")
    public String showCreateNoteForm(@PathVariable String courseName, Model model) {
        model.addAttribute("courseName", courseName);
        model.addAttribute("note", new Note());
        return "newNote";
    }


    @PostMapping("/course/{courseName}/new")
    public String createNote(@PathVariable String courseName, @ModelAttribute Note note, Model model) {
        Boolean isAlreadyCreated = false;
        List<Note> notes = noteService.getNotesForLoggedInUserAndCourse(courseName);
        for (Note n : notes) {
            if(n.getTitle().equals(note.getTitle())) {
                isAlreadyCreated = true;
            }
        }
        if(note.getContent().length()>65535){
            model.addAttribute("errorMessage", "Note is too long.");
            return "newNote";
        }
        if(isAlreadyCreated) {
            model.addAttribute("errorMessage", "Note with this title already exists.");
            return "newNote";
        }
        noteService.addNote(courseName, note);
        return "redirect:/course/" + courseName;
    }
}
