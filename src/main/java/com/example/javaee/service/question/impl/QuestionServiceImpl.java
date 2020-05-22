package com.example.javaee.service.question.impl;

import com.example.javaee.entity.question.Question1;
import com.example.javaee.mapper.question.QuestionMapper;
import com.example.javaee.service.question.QuestionService;
import org.omg.PortableInterceptor.INACTIVE;
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
    public List<Question1> getAll() throws Exception{
        return questionMapper.getAll();
    }

    @Override
    public boolean insertNewQuestion(Question1 question) throws Exception{
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
    public boolean deleteQuestion(Integer qid){
        return questionMapper.deleteQuestion(qid);
    }

    @Override
    public List<Question1> searchSubject(String subject){
        return questionMapper.searchSubject(subject);
    }

    @Override
    public List<Question1> searchQuestion(String question){
        return questionMapper.searchQuestion(question);
    }

    @Override
    public List<Question1> searchQuestionBySubject(String subject,String question){
        return questionMapper.searchQuestionBySubject(subject,question);
    }

    @Override
    public boolean updateQuestion(Question1 question){
        return questionMapper.updateQuestion(question);
    }
}
