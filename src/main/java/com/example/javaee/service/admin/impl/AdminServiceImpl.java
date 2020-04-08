package com.example.javaee.service.admin.impl;

import com.example.javaee.entity.admin.Admin;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import com.example.javaee.mapper.admin.AdminMapper;
import com.example.javaee.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 22:13 2019/12/3
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin)throws Exception{
        return adminMapper.login(admin);
    }

    @Override
    public int update(Admin admin)throws Exception{
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int updateFeelBack(FeelBack feelBack)throws Exception{
        return adminMapper.updateFeelBack(feelBack);
    }

    @Override
    public int deleteFeelBack(int id)throws Exception{
        return adminMapper.deleteFeelBack(id);
    }

    @Override
    public List<FeelBack> listFeelBack()throws Exception{
        return adminMapper.listFeelBack();
    }

    @Override
    public int updateUser(User user)throws Exception{
        return adminMapper.updateUser(user);
    }
    @Override
    public int updateReport(Report report)throws Exception{
        return adminMapper.updateReport(report);
    }
    @Override
    public int addReport(Report report)throws Exception{
        return adminMapper.addReport(report);
    }
    @Override
    public Admin queryAdmin(int id)throws Exception{
        return adminMapper.queryAdmin(id);
    }
}
