package com.example.javaee.service.question;

import com.example.javaee.entity.question.Question1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */
public interface QuestionService {
    List<Question1> getAll() throws Exception;
    Integer insertNewQuestion(Question1 question) throws Exception;
    String getAnswer(Integer qid);
    boolean firstJudge(String answer,Integer id);
    List<String> secondJudge(String answer,Integer qid);
    boolean deleteQuestion(Integer qid);
    List<Question1> searchSubject(String subject);
    List<Question1> searchQuestion(String question);
    List<Question1> searchQuestionBySubject(String subject,String question);
    boolean updateQuestion(Question1 question);
}
