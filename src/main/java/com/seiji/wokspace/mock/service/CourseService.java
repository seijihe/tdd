package com.seiji.wokspace.mock.service;

import java.util.List;

public interface CourseService {

    public List<String> retrieveCourse(String student);
    
    /* 
        With this adjust you have to update stub, that also implements that interface
        Eventhought will not use that. Take a look, it's a null empty implmentation.
    */
    public List<String> doSomething(String student);

    public void deleteCourse(String course);

}
