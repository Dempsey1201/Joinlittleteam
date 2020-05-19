package com.example.javaee.service.paper.impl;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.mapper.answer.AnswerMapper;
import com.example.javaee.mapper.paper.PaperMapper;
import com.example.javaee.mapper.scoreMapper.ScoreMapper;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * author dongml
 * time 2020/3/25
 * description
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;

    @Autowired
    ScoreMapper scoreMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public Integer getScore(Integer sid,Integer pid){
        return scoreMapper.getScore(sid,pid);
    }

    @Override
    public List<UtilClass> getScoreByUser(Integer sid){
        return scoreMapper.getScoreByUser(sid);
    }

    @Override
    public List<UtilClass> getClassAnswer(Integer sid,Integer qid,Integer pid){
        return answerMapper.getClassAnswer(sid,qid,pid);
    }

    @Override
    public List<Paper> getPaperByClass(Integer classno){
        return answerMapper.getPaperByClass(classno);
    }

    @Override
    public boolean insertAnswer(Integer sid,Integer qid,String answer){
        if(answerMapper.getAnswer(sid,qid)!= null){
            return false;
        }
        return answerMapper.insertAnswer(sid,qid,answer);
    }

    @Override
    public boolean correctByTeacher(Integer qid,Integer sid,Integer getscore){
        return answerMapper.correctByTeacher(qid,sid,getscore);
    }

}
