package com.example.javaee.mapper.teacher;

import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.teacher.Teacher;
import com.example.javaee.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 20:57 2020/2/25
 */
@Repository
@Mapper
public interface TeacherMapper {
    /**
     * 登录
     * @param teacher
     * @return
     * @throws Exception
     */
    public Teacher login(@Param("teacher")Teacher teacher) throws Exception;

    /**
     * 注册
     * @param teacher
     * @return
     * @throws Exception
     */
    public int addTeacher(@Param("teacher")Teacher teacher)throws Exception;

    /**
     * 更新个人信息
     * @param teacher
     * @return
     * @throws Exception
     */
    public int updateTeacher(@Param("teacher")Teacher teacher)throws Exception;
    /**
     * 更新密码
     * @param teacher
     * @return
     * @throws Exception
     */
    public int updatePassword(@Param("teacher")Teacher teacher)throws Exception;

    /**
     * 更新状态
     * @param id
     * @return
     * @throws Exception
     */
    //public int updateStatus(@Param("id")int id,@Param("status")int status)throws Exception;

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    public int deleteTeacher(@Param("id")int id)throws Exception;

    /**
     * 列出所有user
     * @return
     * @throws Exception
     */
    public List<Teacher> listTeacher()throws Exception;
    /**
     * 查询
     * @return
     * @throws Exception
     */
    public List<Teacher> queryTeacher(@Param("card")String card)throws Exception;
    /**
     * 查询------------------------有问题
     * @return
     * @throws Exception
     */
    public List<Report> queryReport(@Param("stu_id")int stu_id)throws Exception;

    /**
     * 上传头像
     * @return
     * @throws Exception
     */
    public int uploadHead(@Param("id")int id,@Param("path")String path,@Param("headUrl")String headUrl)throws Exception;
    /**
     * 增加反馈------再考虑要不要
     * @param feelBack
     * @return
     * @throws Exception
     */
    public int addFeelBack(@Param("feelBack") FeelBack feelBack)throws Exception;
}
