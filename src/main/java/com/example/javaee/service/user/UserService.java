package com.example.javaee.service.user;

import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 22:12 2019/12/3
 */
public interface UserService {

    public User login(User user) throws Exception;

    public int update(User user) throws Exception;

    public int updatePassword(User user) throws Exception;

    public int updateStatus(int id,int status) throws Exception;

    public List<User> list()throws Exception;

    public User queryUser(int id)throws Exception;

    public User query(String email)throws Exception;

    public int delete(int id)throws Exception;

    public int addUser(User user)throws Exception;

    public boolean judgeUsername(String username)throws Exception;

    public int uploadHead(int id,String path,String headUrl)throws Exception;

    public List<Report> queryReport(int id)throws Exception;

    public int addFeelBack(FeelBack feelBack)throws Exception;
}
