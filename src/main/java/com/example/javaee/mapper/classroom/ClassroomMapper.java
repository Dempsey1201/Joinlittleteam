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
    public Classroom queryClassroom(@Param("id")int id)throws Exception;

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public Classroom query(@Param("str")String str)throws Exception;

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
     *
     * @param
     * @return
     * @throws Exception
     */
    public int join(@Param("idStr")String idStr,@Param("cid")int cid)throws Exception;
    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public int joinB(@Param("id")int id,@Param("cidStr")String cidStr)throws Exception;

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public int out(@Param("idStr")String idStr,@Param("cid")int cid)throws Exception;
    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public int outB(@Param("id")int id,@Param("cidStr")String cidStr)throws Exception;



    /**
     * 列出所有
     * @return
     * @throws Exception
     */
    public List<Classroom> listClassroom()throws Exception;
}
