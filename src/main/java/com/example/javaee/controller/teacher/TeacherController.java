package com.example.javaee.controller.teacher;

import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 0:02 2020/2/27
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/list")
    public List<Teacher> list() throws Exception{
        return teacherService.list();
    }
    @RequestMapping(value = "/delete")
    public int delete(int id) throws Exception{
        return teacherService.delete(id);
    }

    @RequestMapping(value = "/query")
    public Teacher query(String card) throws Exception{
        return teacherService.queryTeacher(card);
    }

    @RequestMapping(value = "/addTeacher")
    public int addTeacher(Teacher teacher) throws Exception{
        String str=teacher.getPassword();
        teacher.setPassword(getMD5String(str));
        return teacherService.addTeacher(teacher);
    }
    @RequestMapping(value = "/login")
    public Teacher login(String card,String password) throws Exception{
        Teacher teacher=new Teacher();
        teacher.setCard(card);
        password=getMD5String(password);
        teacher.setPassword(password);
        return teacherService.login(teacher);
    }
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
