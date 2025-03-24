package MarcinGarcin.ToDoApp.Course;

import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveCourseAndReturnSavedCourse() {
        User user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");
        userRepository.save(user);

        Course course = new Course();
        course.setCourseName("Test Course");
        course.setUser(user);

        Course savedCourse = courseRepository.save(course);

        Assertions.assertNotNull(savedCourse);
        Assertions.assertTrue(savedCourse.getId() > 0);
        }
    @Test
    public void shouldReturnMoreThanOneSavedCourse() {
        User user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");
        userRepository.save(user);

        Course course1 = new Course();
        course1.setCourseName("Test Course");
        course1.setUser(user);

        Course course2 = new Course();
        course2.setCourseName("Test Course2");
        course2.setUser(user);

        courseRepository.save(course1);
        courseRepository.save(course2);

        List<Course> savedCourses = courseRepository.findAll();
        Assertions.assertNotNull(savedCourses);
        Assertions.assertEquals(2, savedCourses.size());
    }
    @Test
    public void shouldReturnCourseByUserId() {
        User user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");
        userRepository.save(user);

        Course course = new Course();
        course.setCourseName("Test Course");
        course.setUser(user);

        courseRepository.save(course);

        Assertions.assertNotNull(courseRepository.findByUserId(user.getId()));
    }
    @Test
    public void shouldReturnCourseByCourseName() {
        User user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");
        userRepository.save(user);

        Course course = new Course();
        course.setCourseName("Test Course");
        course.setUser(user);

        courseRepository.save(course);

        Assertions.assertNotNull(courseRepository.findByCourseName("Test Course"));
    }
    @Test
    public void shouldReturnEmptyListWhenUserHasNoCourses() {
        User user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");
        userRepository.save(user);

        List<Course> courses = courseRepository.findByUserId(user.getId());

        Assertions.assertNotNull(courses);
        Assertions.assertTrue(courses.isEmpty());
    }
}

