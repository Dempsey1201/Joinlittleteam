package com.example.javaee.service.question;

import com.example.javaee.entity.question.Question;

import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */
public interface QuestionService {
    List<Question> getAll() throws Exception;
    boolean insertNewQuestion(Question question) throws Exception;
}
