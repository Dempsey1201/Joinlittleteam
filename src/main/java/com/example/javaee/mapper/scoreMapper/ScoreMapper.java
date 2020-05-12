package com.example.javaee.mapper.scoreMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author dongml
 * time 12/5/2020
 * description
 */
@Mapper
public interface ScoreMapper {

    Integer getScore(@Param("sid") Integer sid,@Param("pid") Integer pid);
}
