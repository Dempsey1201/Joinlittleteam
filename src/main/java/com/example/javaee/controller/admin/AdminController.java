package com.example.javaee.controller.admin;

import com.example.javaee.entity.admin.Admin;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.admin.AdminService;
import com.example.javaee.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 22:15 2019/12/3
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login")
    public Admin login(String admin_card, String password) throws Exception{
        Admin admin=new Admin();
        admin.setCard(admin_card);
        password=getMD5String(password);
        admin.setPassword(password);
        return adminService.login(admin);
    }

    @RequestMapping(value = "/queryAdmin")
    public Admin queryAdmin(int id)throws Exception{
        return adminService.queryAdmin(id);
    }

    @RequestMapping(value = "/update")
    public int update(Admin admin) throws Exception{
        admin.setPassword(getMD5String(admin.getPassword()));
        return adminService.update(admin);
    }
//    @RequestMapping(value = "/addReport")
//    public int addReport(Report report) throws Exception{
//        return adminService.addReport(report);
//    }
//    @RequestMapping(value = "/updateUser")
//    public int updateUser(User user) throws Exception{
//        return adminService.updateUser(user);
//    }
//    @RequestMapping(value = "/updateReport")
//    public int updateReport(Report report) throws Exception{
//        return adminService.updateReport(report);
//    }
//    @RequestMapping(value = "/updateFeelBack")
//    public int updateFeelBack(FeelBack feelBack) throws Exception{
//        return adminService.updateFeelBack(feelBack);
//    }
//    @RequestMapping(value = "/deleteFeelBack")
//    public int deleteFeelBack(int id) throws Exception{
//        return adminService.deleteFeelBack(id);
//    }
//    @RequestMapping(value = "/listFeelBack")
//    public List<FeelBack> listFeelBack() throws Exception{
//        return adminService.listFeelBack();
//    }
    public static String getMD5String(String str) {
        try {
            //利用Java自带的MessageDigest类实现的最简单的MD5加密方法
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
