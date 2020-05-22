package com.example.javaee.service.teacher.impl;

import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.mapper.teacher.TeacherMapper;
import com.example.javaee.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:52 2020/2/26
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 登录
     * @param teacher
     * @return
     * @throws Exception
     */
    @Override
    public Teacher login(Teacher teacher) throws Exception{
        return teacherMapper.login(teacher);
    }

    /**
     * 注册
     * @param teacher
     * @return
     * @throws Exception
     */
    @Override
    public int addTeacher(Teacher teacher) throws Exception{
        return teacherMapper.addTeacher(teacher);
    }

    /**
     * 修改个人信息
     * @param teacher
     * @return
     * @throws Exception
     */
    @Override
    public int update(Teacher teacher) throws Exception{
        return teacherMapper.updateTeacher(teacher);
    }
    /**
     * 修改密码
     * @param teacher
     * @return
     * @throws Exception
     */
    @Override
    public int updateTeacherPassword(Teacher teacher) throws Exception{
        return teacherMapper.updatePassword(teacher);
    }
    /**
     * 修改状态
     * @param
     * @return
     * @throws Exception
     */
//    @Override
//    public int updateStatus(int id,int status) throws Exception{
//        return teacherMapper.updateStatus(id,status);
//    }
    @Override
    public List<Teacher> list()throws Exception{
        return teacherMapper.listTeacher();
    }
    @Override
    public Teacher queryTeacher(String card)throws Exception{
        return teacherMapper.queryTeacher(card);
    }
    @Override
    public List<Report> queryReport(int id)throws Exception{
        return teacherMapper.queryReport(id);
    }
    @Override
    public int delete(int id)throws Exception{
        return teacherMapper.deleteTeacher(id);
    }
    /**
     * 判断用户名
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public boolean judgeUsername(String name) throws Exception{
        boolean b=true;
        List<Teacher> list=teacherMapper.listTeacher();
        for(int i=0;i<list.size();i++){
            Teacher teacher=list.get(i);
            if(teacher.getTeachername().equals(name)){
                b=false;
                return b;
            }
        }
        return b;
    }
    /**
     * 上传头像
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public int uploadHead(int id,String path,String headUrl) throws Exception{
        return teacherMapper.uploadHead(id,path,headUrl);

    }
    /**
     * 注册
     * @param feelBack
     * @return
     * @throws Exception
     */
    @Override
    public int addFeelBack(FeelBack feelBack) throws Exception{
        return teacherMapper.addFeelBack(feelBack);
    }



}
