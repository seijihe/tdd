package com.seiji.workspace.mock.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.seiji.wokspace.mock.business.CourseBusiness;
import com.seiji.wokspace.mock.service.CourseService;
import com.seiji.workspace.mock.service.stubs.CourseServiceStub;

public class CourseBusinessStubTest {

    /*
        CourseBusiness is the SUT, while stubCourseService is a dependency 
        that may be mocked or stubbed.

        In this examples it's like an Controller that receives an Service injection
    */
    
    @Test 
    void testCourseRelatedToSpring_When_UsingAStub() {

        // Arrange
        CourseService stubCourseService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubCourseService);

        // Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Bill");

        // Assert
        assertEquals(2, filteredCourses.size());

    }

    @Test 
    void testCourseRelatedToSpring_When_UsingAFooBarStub() {

        // Arrange
        CourseService stubCourseService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubCourseService);

        // Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");

        // Assert
        assertEquals(0, filteredCourses.size());

    }

}
