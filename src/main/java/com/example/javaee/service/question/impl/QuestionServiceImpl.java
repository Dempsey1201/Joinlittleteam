package com.example.javaee.service.question.impl;

import com.example.javaee.entity.question.Question;
import com.example.javaee.mapper.question.QuestionMapper;
import com.example.javaee.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<Question> getAll() throws Exception{
        return questionMapper.getAll();
    }

    @Override
    public boolean insertNewQuestion(Question question) throws Exception{
        return questionMapper.insertNewQuestion(question);
    }
}
