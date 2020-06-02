package com.example.javaee.controller.classroom;

import com.example.javaee.entity.classroom.Classroom;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.classroom.ClassroomService;
import com.example.javaee.service.user.UserService;
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

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addClassroom")
    public Classroom addClassroom(Classroom classroom) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date=new Date();
        classroom.setClassno(getMD5String(sdf.format(new Date())));
        classroomService.addClassroom(classroom);
        return classroomService.queryClassroom(classroom.getClassno());
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

    @RequestMapping(value = "/joinClassRoom")
    public int joinClassRoom(int id,String classno) throws Exception{
        int cid=classroomService.queryClassroom(classno).getId();
        return classroomService.joinClassroom(id,cid);
    }

    @RequestMapping(value = "/outClassRoom")
    public int outClassRoom(int id,int cid) throws Exception{
        return classroomService.outClassroom(id,cid);
    }

    @RequestMapping(value = "/query")
    public Classroom query(int id) throws Exception{
        return classroomService.query(id);
    }

    @RequestMapping(value = "/queryClassroom")
    public Classroom queryClassRoom(String classno) throws Exception{
        return classroomService.queryClassroom(classno);
    }

    @RequestMapping(value = "/queryUserClassroom")
    public List<Classroom> queryUserClassRoom(int id) throws Exception{
        User user=userService.queryUser(id);
        if (user==null){
            return null;
        }
        List<Classroom> list=classroomService.queryUserClassroom(user.getClassno());
        if (list.size()==0){
            return null;
        }
        else {return classroomService.queryUserClassroom(user.getClassno());}

    }
    @RequestMapping(value = "/queryTeaClassroom")
    public List<Classroom> queryTeaClassRoom(int id) throws Exception{
        return classroomService.queryTeaClassroom(id);
    }

    @RequestMapping(value = "/queryLike")
    public List<Classroom> queryLike(String name) throws Exception{
        return classroomService.queryLike(name);
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
