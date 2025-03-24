package MarcinGarcin.ToDoApp.Note;


import MarcinGarcin.ToDoApp.Course.Course;
import MarcinGarcin.ToDoApp.Course.CourseRepository;
import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void shouldSaveNoteAndReturnSavedNote() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");

        Course course = new Course();
        course.setCourseName("test");
        course.setUser(user);

        Note note = new Note();
        note.setCourse(course);
        note.setUser(user);
        note.setTitle("test");
        note.setContent("testContent");

        Note savedNote = noteRepository.save(note);

        Assertions.assertNotNull(savedNote);
        Assertions.assertEquals(note.getId(), savedNote.getId());
    }

    @Test
    public void shouldReturnNotesByCourseAndUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);

        Course course = new Course();
        course.setCourseName("test");
        course.setUser(user);
        courseRepository.save(course);

        Note note = new Note();
        note.setCourse(course);
        note.setUser(user);
        note.setTitle("test");
        note.setContent("testContent");

        Note note1 = new Note();
        note1.setCourse(course);
        note1.setUser(user);
        note1.setTitle("test1");
        note1.setContent("testContent");

        noteRepository.save(note);
        noteRepository.save(note1);

        List<Note> notes = noteRepository.findByCourseAndUser(course, user);

        Assertions.assertFalse(notes.isEmpty());
        Assertions.assertEquals(2, notes.size());
    }

    @Test
    public void shouldDeleteNote() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);

        Course course = new Course();
        course.setCourseName("test");
        course.setUser(user);
        courseRepository.save(course);

        Note note = new Note();
        note.setCourse(course);
        note.setUser(user);
        note.setTitle("test");
        note.setContent("testContent");
        noteRepository.save(note);

        List<Note> beforeDelete = noteRepository.findAll();
        noteRepository.delete(note);
        List<Note> afterDelete = noteRepository.findAll();

        Assertions.assertEquals(1,beforeDelete.size());
        Assertions.assertEquals(0,afterDelete.size());
    }

    @Test
    public void shouldReturnNotesOnlyOwnedByUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);

        User user1 = new User();
        user.setUsername("test1");
        user.setPassword("test1");
        userRepository.save(user1);

        Course course = new Course();
        course.setCourseName("test");
        course.setUser(user);
        courseRepository.save(course);

        Course course1 = new Course();
        course.setCourseName("test1");
        course.setUser(user1);
        courseRepository.save(course1);

        Note note = new Note();
        note.setCourse(course);
        note.setUser(user);
        note.setTitle("test");
        note.setContent("testContent");
        noteRepository.save(note);

        Note note1 = new Note();
        note1.setCourse(course1);
        note1.setUser(user1);
        note1.setTitle("test1");
        note1.setContent("testContent1");
        noteRepository.save(note);
        List<Note> notes = noteRepository.findByCourseAndUser(course, user);

        Assertions.assertEquals(1,notes.size());
    }



}
