package com.example.javaee.test;

import com.example.javaee.JavaeeApplication;
import com.example.javaee.controller.admin.AdminController;
import com.example.javaee.entity.admin.Admin;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 20:02 2019/12/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaeeApplication.class)
public class AdminTest {
    @Autowired
    AdminController adminController;

    @Test
    public void testLogin()throws Exception{
        System.out.println(adminController.login("123123","test"));
    }

    @Test
    public void testUpdate()throws Exception{
        Admin admin=new Admin();
        admin.setId(2);
        admin.setPassword("test123`");
        admin.setAdminname("test22");
        System.out.println(adminController.update(admin));
    }
//    @Test
//    public void testUpdateUser()throws Exception{
//        User admin=new User();
//        admin.setId(88);
//        admin.setMajor("rjgc");
//        admin.setCollege("sdasdasd");
//        System.out.println(adminController.updateUser(admin));
//    }
//    @Test
//    public void testUpdateReport()throws Exception{
//        Report report=new Report();
//        report.setClass_name("绿色化学");
//        report.setId(18);
//        report.setScore("100");
//        report.setPoint("2");
//        System.out.println(adminController.updateReport(report));
//    }
//    @Test
//    public void testUpdateFeelBack()throws Exception{
//        FeelBack feelBack=new FeelBack();
//        feelBack.setId(12);
//        feelBack.setStatus(1);
//        System.out.println(adminController.updateFeelBack(feelBack));
//    }
    @Test
    public void testQuery()throws Exception{
        System.out.println(adminController.queryAdmin(1));
    }
//    @Test
//    public void testDeleteFeelBack()throws Exception{
//        System.out.println(adminController.deleteFeelBack(13));
//    }
}
