package com.example.javaee.mapper.scoreMapper;

import com.example.javaee.entity.utilClass.UtilClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * author dongml
 * time 12/5/2020
 * description
 */
@Mapper
public interface ScoreMapper {

    Integer getScore(@Param("sid") Integer sid,@Param("pid") Integer pid);
    List<UtilClass> getScoreByUser(Integer sid);
}
