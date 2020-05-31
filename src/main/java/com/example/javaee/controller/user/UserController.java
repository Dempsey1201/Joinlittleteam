package com.example.javaee.controller.user;

import com.example.javaee.controller.classroom.ClassroomController;
import com.example.javaee.entity.classroom.Classroom;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.classroom.ClassroomService;
import com.example.javaee.service.user.UserService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64.Decoder;
/**
 * @Author: Yangfn
 * @Description:
 * @Date: 22:15 2019/12/3
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ClassroomController classroomController;

    @RequestMapping(value = "/login")
    public User login(String email,String password) throws Exception{
        User user = new User();
        user.setEmail(email);
        password=getMD5String(password);
        user.setPassword(password);
        return userService.login(user);
    }

    @RequestMapping(value = "/updateUser")
    public int update(User user) throws Exception{
        return userService.update(user);
    }

    @RequestMapping(value = "/updateUserStatus")
    public int updateUserStatus(int id,int status) throws Exception{
        return userService.updateStatus(id,status);
    }

    @RequestMapping(value = "/addUser")
    public int addUser(User user) throws Exception{
        String str=user.getPassword();
        user.setPassword(getMD5String(str));
        user.setHeadUrl("/img/user.png");
        user.setPath("/yfn/user.png");
        return userService.addUser(user);
    }

    @RequestMapping(value = "/list")
    public List<User> list() throws Exception{
        return userService.list();
    }

    @RequestMapping(value = "/queryUser")
    public User queryUser(int id) throws Exception{
        return userService.queryUser(id);
    }

    @RequestMapping(value = "/queryClass")
    public List<User> queryClass(int id) throws Exception{
        return userService.queryClass(id);
    }

    @RequestMapping(value = "/query")
    public User query(String email) throws Exception{
        return userService.query(email);
    }

    @RequestMapping(value = "/email")
    public String email(String email) throws Exception{
        String code=getRandom();
        Boolean b=sendEmail(email,code);
        if(b==true){
            return code;
        }
        else{return "fail!!";}
    }
    @RequestMapping(value = "/delete")
    public int delete(@RequestParam(value = "id", required = false)int id) throws Exception{
        String s=userService.queryUser(id).getClassno();
        if(s!=null){
            String[] arr=userService.queryUser(id).getClassno().split(",");
            for(String x:arr){
                if(x.equals("")){
                    int n=Integer.parseInt(x);
                    classroomController.outClassRoom(id,n);
                }

            }
        }
        return userService.delete(id);
    }
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(User user) throws Exception{
        String str=user.getPassword();
        user.setPassword(getMD5String(str));
        userService.updatePassword(user);
        return user.getPassword();
    }

    @ResponseBody
    @RequestMapping(value = "/uploadHead",method = RequestMethod.POST)
    public String pictureupload(@RequestParam(value = "imgStr", required=false)String imgStr,int id)throws Exception{
        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return "fail";
        Decoder decoder = Base64.getDecoder();
        String words_to = "/yfn/";
        //String words_to = "e://yfn/";
        String son = id+"user"+getRandom()+".jpg";
        String path = words_to +son;
        String url = "/img/"+son;
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();

            String filename=userService.queryUser(id).getPath();
            File file=new File(filename);
            String[] strArray = filename.split("\\.");
            int suffixIndex = strArray.length -1;
            System.out.println(strArray[suffixIndex]);
            if (strArray[suffixIndex].equals("jpg")){
                file.delete();
            }
            userService.uploadHead(id,path,url);
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
    public static boolean sendEmail(String emailaddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("120.241.25.80");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setSSLOnConnect(true);
            email.setSslSmtpPort("465");
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom("1240208775@qq.com", "成才考试系统");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication("1240208775@qq.com", "tgkzxmtylhengbdi");//此处填写邮箱地址和客户端授权码
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            email.setSubject("成才考试系统-用户注册邮箱验证");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您于"+sdf.format(new Date())+"在本系统进行注册操作，本次注册的验证码是:" + code+"<br>如非本人操作请勿理睬");//此处填写邮件内容

            email.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
