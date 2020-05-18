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
        String str=user.getPassword();
        user.setPassword(getMD5String(str));
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
    public List<User> queryUser(int id) throws Exception{
        return userService.queryUser(id);
    }
    @RequestMapping(value = "/queryReport")
    public List<Report> queryReport(int id) throws Exception{
        return userService.queryReport(id);
    }
    @RequestMapping(value = "/delete")
    public int delete(@RequestParam(value = "id", required = false)int id) throws Exception{
        return userService.delete(id);
    }
    @RequestMapping(value = "/updatePassword")
    public int updatePassword(User user) throws Exception{
        String str=user.getPassword();
        user.setPassword(getMD5String(str));
        return userService.updatePassword(user);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadHead")
    public int pictureupload(@RequestParam(value = "imgStr", required=false)String imgStr,int id)throws Exception{
        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return 0;
        Decoder decoder = Base64.getDecoder();
//      String words_to = "/opt/yfn/upload/user";
        String words_to = "E://yfn/";
        String son = id+".jpg";
        String imgFilePath = words_to +son;
        String host = "/img/"+son;
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            userService.uploadHead(id,imgFilePath,host);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    @RequestMapping(value = "/addFeelBack")
    public int addFeelBack(FeelBack feelBack) throws Exception{
        return userService.addFeelBack(feelBack);
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
