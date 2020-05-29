package com.example.javaee.mapper.answer;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.utilClass.UtilClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author dongml
 * time 18/5/2020
 * description
 */
@Repository
@Mapper
public interface AnswerMapper {

    List<UtilClass> getClassAnswer(Integer sid,Integer qid,Integer pid);
    List<Paper> getPaperByClass(@Param("classno") Integer classno);
    boolean insertAnswer(@Param("sid") Integer sid,@Param("qid") Integer qid,@Param("answer") String answer);
    String getUserAnswer(Integer sid,Integer qid);
    boolean correctByTeacher(Integer qid,Integer sid,Integer getscore);
    List<Paper> getPaperByClass2(Integer classno);
}
