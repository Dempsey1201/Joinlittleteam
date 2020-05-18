package com.example.javaee.controller.classroom;

import com.example.javaee.entity.classroom.Classroom;
import com.example.javaee.service.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:49 2020/4/11
 */
@RestController
@CrossOrigin
@RequestMapping("/class")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    @RequestMapping(value = "/addClassroom")
    public int addClassroom(Classroom classroom) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date=new Date();
        classroom.setClassno(getMD5String(sdf.format(new Date())));
        return classroomService.addClassroom(classroom);
    }

    @RequestMapping(value = "/list")
    public List<Classroom> listClassroom() throws Exception{
        return classroomService.list();
    }

    @RequestMapping(value = "/delete")
    public int deleteClassroom(int id) throws Exception{
        return classroomService.deleteClassroom(id);
    }

    @RequestMapping(value = "/update")
    public int updateClassroom(Classroom classroom) throws Exception{
        return classroomService.update(classroom);
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

//        char secret='8';    //加密文字符
//
//        System.out.println("请输入您想加密的内容：");
//
//        String pass=str;
//
//        System.out.println("原字符串内容："+pass);
//
//        String secretResult= secret(pass,secret);
    }


}
