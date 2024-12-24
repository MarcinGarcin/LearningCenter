package MarcinGarcin.ToDoApp.Note;


import MarcinGarcin.ToDoApp.Course.Course;
import MarcinGarcin.ToDoApp.Course.CourseRepository;
import MarcinGarcin.ToDoApp.user.User;
import MarcinGarcin.ToDoApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
    public Optional<Note> getNoteFromId(Long id){
        return noteRepository.findById(id);
    }

    public void updateNote(long noteId, Note updatedNote) {
        Optional<Note> existingNoteOpt = noteRepository.findById(noteId);

        if (existingNoteOpt.isPresent()) {
            Note existingNote = existingNoteOpt.get();
            existingNote.setContent(updatedNote.getContent());
            noteRepository.save(existingNote);
        }
    }

    public void addNote(String courseName,Note note) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUser = userRepository.findByUsername(username);
        Optional<Course> currentCourse = courseRepository.findByCourseName(courseName);

        note.setUser(loggedInUser.get());
        note.setCourse(currentCourse.get());
        noteRepository.save(note);
    }

    public void deleteNote(String courseName, Long noteId) {
        String username = getLoggedInUsername();
        Optional<User> loggedInUserOpt = userRepository.findByUsername(username);
        Optional<Course> courseOpt = courseRepository.findByCourseName(courseName);
        Optional<Note> noteOpt = noteRepository.findById(noteId);

        if (loggedInUserOpt.isPresent() && courseOpt.isPresent() && noteOpt.isPresent()) {
            User loggedInUser = loggedInUserOpt.get();
            Course course = courseOpt.get();
            Note note = noteOpt.get();

            if (note.getCourse().equals(course) && course.getUser().equals(loggedInUser)) {
                noteRepository.delete(note); // Perform deletion
            }
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
