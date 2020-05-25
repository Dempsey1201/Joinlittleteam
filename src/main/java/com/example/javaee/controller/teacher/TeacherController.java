package com.example.javaee.controller.teacher;

import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;
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
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(Teacher teacher) throws Exception{
        String str=teacher.getPassword();
        teacher.setPassword(getMD5String(str));
        teacherService.updateTeacherPassword(teacher);
        return teacher.getPassword();
    }

    @RequestMapping(value = "/updateTeacher")
    public int updateTeacher(Teacher teacher) throws Exception{
        return teacherService.update(teacher);
    }
    @RequestMapping(value = "/login")
    public Teacher login(String card,String password) throws Exception{
        Teacher teacher=new Teacher();
        teacher.setCard(card);
        password=getMD5String(password);
        teacher.setPassword(password);
        return teacherService.login(teacher);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadHead",method = RequestMethod.POST)
    public int pictureupload(@RequestParam(value = "imgStr", required=false)String imgStr, int id)throws Exception{

        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return 0;
        System.out.println("begin");
        Base64.Decoder decoder = Base64.getDecoder();
        //String words_to = "e:/yfn/";
        String words_to = "/yfn/";
        String son = id+"tea"+getRandom()+".jpg";
        String path = words_to +son;
        String url = "/img/"+son;
        System.out.println("tea-head-try-catch");
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            System.out.println("path:"+path);
            System.out.println("url:"+url);
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            System.out.println("teaService.uploadHead");
            teacherService.uploadHead(id,path,url);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getRandom() {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, 6));
        return String.valueOf(rs);
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
