package com.seiji.wokspace.mock.business;

import java.util.ArrayList;
import java.util.List;
import com.seiji.wokspace.mock.service.CourseService;

// System (Method) Under Test (SUT)
public class CourseBusiness {
    
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student){

        var filteredCourses = new ArrayList<String>();

        if ("Foo Bar".equals(student))
            return filteredCourses;

        var allCourses = service.retrieveCourse(student);

        allCourses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(filteredCourses::add);

        return filteredCourses;
    }

    public void deleteCourseNotRelatedToSpring(String student){

        var allCourses = service.retrieveCourse(student);

        allCourses.stream()
                .filter(course -> !course.contains("Spring"))
                .forEach(service::deleteCourse); // That implementation in empty. Howerver the important point here is confirm at mockito verify() if that method is triggered
    }

}
