package com.example.javaee.test;

import com.example.javaee.JavaeeApplication;
import com.example.javaee.controller.teacher.TeacherController;
import com.example.javaee.controller.user.UserController;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 10:42 2019/12/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaeeApplication.class)
public class UserTest {
    @Autowired
    UserController userController;
    @Autowired
    TeacherController teacherController;

    @Test
    public void testList()throws Exception{
        System.out.println(userController.list());
    }
    @Test
    public void testLogin()throws Exception{
        System.out.println(userController.login("test","test"));
    }
    @Test
    public void testAddUser()throws Exception{
        User user=new User();
        user.setUsername("name");
        user.setEmail("1240208775@qq.com");
        user.setPassword("test");
        user.setSex("女");
        System.out.println(userController.addUser(user));
    }
    @Test
    public void testQueryUser()throws Exception{
        System.out.println(userController.queryUser(1));
    }
    @Test
    public void testQuery()throws Exception{
        System.out.println(userController.query("test"));
    }
    @Test
    public void testEmail()throws Exception{
        System.out.println(userController.email("1240208775@qq.com"));
    }
    @Test
    public void testQueryClass()throws Exception{
        System.out.println(userController.queryClass(32));
    }
    @Test
    public void testDeleteUser()throws Exception{
        System.out.println(userController.delete(29));
    }
    @Test
    public void testUpdateUser()throws Exception{
        User user=new User();
        user.setId(20);
        user.setUsername("testUpdate");
        user.setEmail("123123@qq.com");
        user.setCollege("123123");
        System.out.println(userController.update(user));
    }
//    @Test
//    public void testUpdateStatus()throws Exception{
//        int id=94,status=1;
//        System.out.println(userController.updateUserStatus(id,status));
//    }

    @Test
    public void testUpdatePassword()throws Exception{
        User user=new User();
        user.setId(4);
        user.setPassword("test");
        System.out.println(userController.updatePassword(user));
    }

    @Test
    public void testUplode()throws Exception{
        System.out.println(teacherController.pictureupload("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",30));
    }

    @Test
    public void testdeletFile()throws Exception{
        String path="e:/yfn/1.jpg";
        File file=new File(path);
        file.delete();
    }

//    @Test
//    public void testQueryReport()throws Exception{
//        System.out.println(userController.queryReport(77));
//    }
//
//    @Test
//    public void testUplode()throws Exception{
//        System.out.println(userController.queryReport(77));
//    }
//    @Test
//    public void testAddFeelBack()throws Exception{
//        FeelBack feelBack=new FeelBack();
//        feelBack.setStu_id(80);
//        feelBack.setStatus(0);
//        feelBack.setMessage("成绩复议");
//        System.out.println(userController.addFeelBack(feelBack));
//    }
}
