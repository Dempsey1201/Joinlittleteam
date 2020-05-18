package com.example.javaee.test;

import com.example.javaee.JavaeeApplication;
import com.example.javaee.controller.classroom.ClassroomController;
import com.example.javaee.entity.classroom.Classroom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 17:00 2020/5/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaeeApplication.class)
public class ClassroomTest {

    @Autowired
    ClassroomController classroomController;
    @Test
    public void testList()throws Exception{
        System.out.println(classroomController.listClassroom());
    }

    @Test
    public void testDelete()throws Exception{
        System.out.println(classroomController.deleteClassroom(10));
    }

    @Test
    public void testUpdate()throws Exception{
        Classroom classroom=new Classroom();
        classroom.setMajor("数据库");
        classroom.setId(2);
        classroom.setClassname("name");
        System.out.println(classroomController.updateClassroom(classroom));
    }

    @Test
    public void testAdd()throws Exception{
        Classroom classroom=new Classroom();
        classroom.setClassname("123123");
        classroom.setTeacherno(1);
        classroom.setTeachername("www");
        classroom.setMajor("软件工程");
        System.out.println(classroomController.addClassroom(classroom));
    }

}
