package com.example.javaee.mapper.question;

import com.example.javaee.entity.question.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
@Mapper
public interface QuestionMapper {


    List<Question> getAll() throws Exception;

    boolean insertNewQuestion(Question question) throws Exception;

    String getAnswer(@Param("qid")Integer qid);

    boolean deleteQuestion(Question question);
}
