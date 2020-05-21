package com.example.javaee.test;

import com.example.javaee.JavaeeApplication;
import com.example.javaee.controller.teacher.TeacherController;
import com.example.javaee.entity.teacher.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 16:40 2020/5/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaeeApplication.class)
public class TeacherTest {
    @Autowired
    TeacherController teacherController;

    @Test
    public void testList()throws Exception{
        System.out.println(teacherController.list());
    }
    @Test
    public void testLogin()throws Exception{
        System.out.println(teacherController.login("201800","test"));
    }
    @Test
    public void testAddUser()throws Exception{
        Teacher teacher=new Teacher();
        teacher.setCard("201800");
        teacher.setTeachername("name");
        teacher.setEmail("test");
        teacher.setPassword("test");
        teacher.setSex("女");
        System.out.println(teacherController.addTeacher(teacher));
    }
    @Test
    public void testUpdateUser()throws Exception{
        Teacher teacher=new Teacher();
        teacher.setTeachername("dongml");
        teacher.setEmail("test");
        teacher.setId(1);
        teacher.setCollege("东北师大");
        System.out.println(teacherController.updateTeacher(teacher));
    }
    @Test
    public void testUpdate()throws Exception{
        Teacher teacher=new Teacher();
        teacher.setId(1);
        teacher.setPassword("test");
        System.out.println(teacherController.updatePassword(teacher));
    }
    @Test
    public void testDelete()throws Exception{
        System.out.println(teacherController.delete(2));
    }
    @Test
    public void testQuery()throws Exception{
        System.out.println(teacherController.query("201800"));
    }
}
