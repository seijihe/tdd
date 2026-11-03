package com.seiji.workspace.mock.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.seiji.wokspace.mock.business.CourseBusiness;
import com.seiji.wokspace.mock.service.CourseService;

public class CourseBusinessMockTest {
    
    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach 
    void setup() {
        mockService = mock(CourseService.class); // new CourseServiceStub();
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "Agile Methodologies",
                "Spring MVC",
                "Spring Boot",
                "Java Fundamentals",
                "Python for Beginners",
                ".NET Core",
                "Linux Administration",
                "Docker Essentials",
                "Kubernetes",
                "JavaScript and React",
                "SQL and Databases");
    }

    @Test 
    void testCourseRelatedToSpring_When_UsingAMock() {
       
        // Arrange
        when(mockService.retrieveCourse("Bill")).thenReturn(courses);

        // Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Bill");

        // Assert
        assertEquals(2, filteredCourses.size());

    }

}
