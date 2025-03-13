package MarcinGarcin.ToDoApp.Course;

import MarcinGarcin.ToDoApp.Note.NoteRepository;
import MarcinGarcin.ToDoApp.User.User;
import MarcinGarcin.ToDoApp.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private CourseService courseService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUsername("testUser");


        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username("testUser")
                .password("password")
                .build();

        SecurityContext securityContext = mock(SecurityContext.class);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetCoursesForLoggedUserWhenUserExists() {
        Course course1 = new Course();
        course1.setCourseName("Course1");

        Course course2 = new Course();
        course2.setCourseName("Course2");


        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(courseRepository.findByUserId(testUser.getId())).thenReturn(List.of(course1, course2));


        List<Course> courses = courseService.getCoursesForLoggedUser();

        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }

    @Test
    void testAddCourseWithSpacesInName() {
        Course course = new Course();
        course.setCourseName("Course Name with Spaces");


        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));


        courseService.addCourse(course);


        verify(courseRepository, times(1)).save(course);
        assertEquals("Course_Name_with_Spaces", course.getCourseName());
    }

    @Test
    void testDeleteCourseById() {
        Course course = new Course();
        course.setId(1L);


        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));


        courseService.deleteById(1L);


        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCourseWhenNoNotesExist() {
        Course course = new Course();
        course.setId(1L);


        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(noteRepository.findByCourseAndUser(course, testUser)).thenReturn(List.of());


        courseService.deleteById(1L);


        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetCoursesForLoggedUserWhenUserDoesNotExist() {

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());


        List<Course> courses = courseService.getCoursesForLoggedUser();

        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }
}
