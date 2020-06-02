package com.example.javaee.service.admin;

import com.example.javaee.entity.admin.Admin;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 22:13 2019/12/3
 */
public interface AdminService {

    public Admin login(Admin admin)throws Exception;

    public Admin queryAdmin(int id)throws Exception;

    public List<FeelBack> listFeelBack()throws Exception;

    public int deleteFeelBack(int id)throws Exception;

    public int updateFeelBack(FeelBack feelBack)throws Exception;

    public int update(Admin admin)throws Exception;

    public int updateUser(User user)throws Exception;

    public int updateReport(Report report)throws Exception;

    public int addReport(Report report)throws Exception;


}
