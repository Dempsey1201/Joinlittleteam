package com.example.javaee.service.teacher;

import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.entity.user.User;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:52 2020/2/26
 */
public interface TeacherService {

    public Teacher login(Teacher teacher) throws Exception;

    public int update(Teacher teacher) throws Exception;

    public int updateTeacherPassword(Teacher teacher) throws Exception;

    public int updateStatus(int id,int status) throws Exception;

    public List<Teacher> list()throws Exception;

    public Teacher queryTeacher(String card)throws Exception;

    public int delete(int id)throws Exception;

    public int addTeacher(Teacher teacher)throws Exception;

    public boolean judgeUsername(String username)throws Exception;

    public int uploadHead(int id,String path,String headUrl)throws Exception;

    public List<Report> queryReport(int id)throws Exception;

    public int addFeelBack(FeelBack feelBack)throws Exception;

}
