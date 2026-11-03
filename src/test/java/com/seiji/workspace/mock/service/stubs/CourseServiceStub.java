package com.seiji.workspace.mock.service.stubs;

import java.util.Arrays;
import java.util.List;

import com.seiji.wokspace.mock.service.CourseService;

public class CourseServiceStub implements CourseService {

    @Override
    public List<String> retrieveCourse(String student) {
        return Arrays.asList(
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

    @Override
    public List<String> doSomething(String student) {
        // TOD O Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doSomething'");
    }

    @Override
    public void deleteCourse(String course) {
        // TOD O Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

    
}
