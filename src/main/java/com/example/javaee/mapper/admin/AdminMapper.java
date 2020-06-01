package com.example.javaee.mapper.admin;

import com.example.javaee.entity.admin.Admin;
import com.example.javaee.entity.feelback.FeelBack;
import com.example.javaee.entity.report.Report;
import com.example.javaee.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 15:01 2019/12/11
 */
@Repository
@Mapper
public interface AdminMapper {
    /**
     * 登录
     * @param admin
     * @return
     * @throws Exception
     */
    public Admin login(@Param("admin")Admin admin) throws Exception;

    /**
     * 更新个人信息
     * @param admin
     * @return
     * @throws Exception
     */
    public int updateAdmin(@Param("admin")Admin admin)throws Exception;
    /**
     * 更新学生信息
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUser(@Param("user") User user)throws Exception;

    /**
     * 更新学生信息
     * @param report
     * @return
     * @throws Exception
     */
    public int updateReport(@Param("report")Report report)throws Exception;

    /**
     * 更新反馈
     * @param feelBack
     * @return
     * @throws Exception
     */
    public int updateFeelBack(@Param("feelback")FeelBack feelBack)throws Exception;

    /**
     * 删除反馈
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteFeelBack(@Param("id")int id)throws Exception;

    /**
     * 更新学生信息
     * @param
     * @return
     * @throws Exception
     */
    public List<FeelBack> listFeelBack()throws Exception;

    /**
     * 查找admin
     * @param id
     * @return
     * @throws Exception
     */
    public Admin queryAdmin(@Param("id")int id)throws Exception;
    /**
     * 上传成绩
     * @param report
     * @return
     * @throws Exception
     */
    public int addReport(@Param("report")Report report)throws Exception;
}
