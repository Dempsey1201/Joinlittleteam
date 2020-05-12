package com.example.javaee.service.question.impl;

import com.example.javaee.entity.question.Question;
import com.example.javaee.mapper.question.QuestionMapper;
import com.example.javaee.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public String getAnswer(Integer qid){
        return questionMapper.getAnswer(qid);
    }

    @Override
    public boolean firstJudge(String answer,Integer qid){
        String answerInSystem = questionMapper.getAnswer(qid);
        return answerInSystem.equals(answer);
    }

    @Override
    public List<String> secondJudge(String answer,Integer qid){
        String answerInSystem = questionMapper.getAnswer(qid);
        List<String> list = new ArrayList<String>();
        int length = answerInSystem.length();
        int temp = 0;
        while(temp < length){
            if(answerInSystem.substring(temp,temp).equals(answer.substring(temp,temp))) {
                temp++;
            }else {
                list.add(answerInSystem.substring(temp,temp));
            }
        }
        return list;
    }

    @Override
    public boolean deleteQuestion(Question question){
        return questionMapper.deleteQuestion(question);
    }
}
