package com.example.javaee.test;

import com.example.javaee.JavaeeApplication;
import com.example.javaee.controller.user.UserController;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
        user.setEmail("test");
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
    public void testQueryClass()throws Exception{
        System.out.println(userController.queryClass(1));
    }
    @Test
    public void testDeleteUser()throws Exception{
        System.out.println(userController.delete(3));
    }
    @Test
    public void testUpdateUser()throws Exception{
        User user=new User();
        user.setId(2);
        user.setUsername("testUpdate");
        user.setEmail("123123@qq.com");
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
