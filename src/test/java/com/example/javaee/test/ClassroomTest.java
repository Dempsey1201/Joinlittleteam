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

    @Test
    public void testJoin()throws Exception{
        System.out.println(classroomController.joinClassRoom(20,"f043a866ea9bb7f9bd5a90a1147ff8e9"));
    }

    @Test
    public void testOut()throws Exception{
        System.out.println(classroomController.outClassRoom(12,34));
    }

    @Test
    public void testQuery()throws Exception{
        System.out.println(classroomController.query(1));
    }

    @Test
    public void testQueryClassroom()throws Exception{
        System.out.println(classroomController.queryClassRoom("aa"));
    }

    @Test
    public void testQueryUserClassroom()throws Exception{
        System.out.println(classroomController.queryUserClassRoom(1));
    }

    @Test
    public void testQueryTeaClassroom()throws Exception{
        System.out.println(classroomController.queryTeaClassRoom(1));
    }
    @Test
    public void testQueryLike()throws Exception{
        System.out.println(classroomController.queryLike("2"));
    }

}
