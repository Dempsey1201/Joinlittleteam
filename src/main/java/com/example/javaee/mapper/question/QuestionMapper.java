package com.example.javaee.mapper.question;

import com.example.javaee.entity.question.Question1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
@Repository
@Mapper
public interface QuestionMapper {


    List<Question1> getAll() throws Exception;

    boolean insertNewQuestion(@Param("question") Question1 question) throws Exception;

    String getAnswer(@Param("qid")Integer qid);

    boolean deleteQuestion(@Param("qid") Integer qid);

    boolean updateQuestion(@Param("question") Question1 question);

    List<Question1> searchSubject(String subject);

    List<Question1> searchQuestion(String question);

    List<Question1> searchQuestionBySubject(String subject,String question);
}
