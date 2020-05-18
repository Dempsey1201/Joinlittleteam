package com.example.javaee.mapper.classroom;

import com.example.javaee.entity.classroom.Classroom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:52 2020/4/11
 */
@Repository
@Mapper
public interface ClassroomMapper {
    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    public int deleteClassroom(@Param("id")int id)throws Exception;

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public int addClassroom(@Param("classroom")Classroom classroom)throws Exception;

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public int update(@Param("classroom")Classroom classroom)throws Exception;
    /**
     * 列出所有
     * @return
     * @throws Exception
     */
    public List<Classroom> listClassroom()throws Exception;
}
