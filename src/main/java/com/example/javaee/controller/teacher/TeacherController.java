package com.example.javaee.controller.teacher;

import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.teacher.TeacherService;
import org.apache.commons.mail.HtmlEmail;
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
        sendEmail(teacher.getEmail(),teacher.getCard(),str);
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
    public String pictureupload(@RequestParam(value = "imgStr", required=false)String imgStr, int id)throws Exception{

        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return "fail";
        Base64.Decoder decoder = Base64.getDecoder();
        //String words_to = "e:/yfn/";
        String words_to = "/yfn/";
        String son = id+"tea"+getRandom()+".jpg";
        String path = words_to +son;
        String url = "/img/"+son;
        //System.out.println("tea-head-try-catch");
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            //System.out.println("path:"+path);
            //System.out.println("url:"+url);
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            System.out.println(id+path+url);
            teacherService.uploadHead(id,path,url);
            return url;
        } catch (Exception e) {
            return "fail";
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
    //邮箱验证码
    public static boolean sendEmail(String emailaddress,String card,String password) {
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom("1240208775@qq.com", "成才考试系统");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication("1240208775@qq.com", "tgkzxmtylhengbdi");//此处填写邮箱地址和客户端授权码

            email.setSubject("成才考试系统-教师账户信息");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您在本系统的教师账户信息如下<br>" + "教师账户工号：" + card + "<br>教师账户初始密码:" + password+"<br>登录后请尽快修改密码");//此处填写邮件内容

            email.send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
