package com.example.javaee.controller.user;

import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import com.example.javaee.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;
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

    @RequestMapping(value = "/delete")
    public int delete(@RequestParam(value = "id", required = false)int id) throws Exception{
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
        //System.out.println("begin");
        Decoder decoder = Base64.getDecoder();
        String words_to = "/yfn/";
        //String words_to = "e://yfn/";
        String son = id+"user"+getRandom()+".jpg";
        String path = words_to +son;
        String url = "/img/"+son;
        System.out.println("user-head-try-catch");
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
            //System.out.println("userService.uploadHead");
            System.out.println(id+path+url);
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
}
