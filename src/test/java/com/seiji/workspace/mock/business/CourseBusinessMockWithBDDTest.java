package com.seiji.workspace.mock.business;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.seiji.wokspace.mock.business.CourseBusiness;
import com.seiji.wokspace.mock.service.CourseService;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseBusinessMockWithBDDTest {
    
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
       
        // Given
        given(mockService.retrieveCourse("Bill")).willReturn(courses);

        // When
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Bill");

        // Then
        assertThat(filteredCourses.size()).isEqualTo(2);

    }

    @Test 
    void testDeleteCoursesNotRelatedSpring_UsingMockitoVerify_ShouldCallDeleteMethod(){

        // Given
        given(mockService.retrieveCourse("Bill")).willReturn(courses);

        // When
        business.deleteCourseNotRelatedToSpring("Bill");

        // Then
        verify(mockService, times(1)).deleteCourse("Python for Beginners");
        // verify(mockService).deleteCourse("Spring MVC"); // That's supposed to failed, because will not call our mocked method
        verify(mockService, never()).deleteCourse("Spring MVC");

        /* 
            Or you can also do until verify(): then().shoud() and never()
            That's more BDD-Style
        */

        then(mockService).should().deleteCourse("Python for Beginners");
        then(mockService).should(never()).deleteCourse("Spring MVC");

    }

    @Test 
    void testDeleteCoursesNotRelatedSpring_CapturingArguments_ShouldCallDeleteMethod(){

        // Given

         courses = Arrays.asList(
                "Spring MVC",
                "Python for Beginners"
                );
                
        given(mockService.retrieveCourse("Bill")).willReturn(courses);
        String pythonCourse = "Python for Beginners";

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // When
        business.deleteCourseNotRelatedToSpring("Bill");

        // Then
        then(mockService).should().deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(pythonCourse);

    }

    @Test 
    void testDeleteCoursesNotRelatedSpring_CapturingMoreThanOnceArguments_ShouldCallDeleteMethod(){

        // Given

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
                
        given(mockService.retrieveCourse("Bill")).willReturn(courses);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // When
        business.deleteCourseNotRelatedToSpring("Bill");

        // Then
        then(mockService).should(times(9)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size()).isEqualTo(9);
        assertThat(argumentCaptor.getAllValues()).hasSize(9);

    }

}
