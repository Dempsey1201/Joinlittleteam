package com.example.javaee.service.paper.impl;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.mapper.answer.AnswerMapper;
import com.example.javaee.mapper.paper.PaperMapper;
import com.example.javaee.mapper.scoreMapper.ScoreMapper;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean storeAnswer(Integer pid,List<Integer>qid,Integer sid,List<String> answer){
        int i  = 0;
        for(i = 0;i < qid.size();i++){
            Integer integer = qid.get(i);
            String string = answer.get(i);
            answerMapper.insertAnswer(sid,integer,string);
            if(answerMapper.getAnswer(sid,integer).equals(string)){
                Integer integer1 = scoreMapper.getQscore(integer,pid);
                answerMapper.correctByTeacher(integer,sid,integer1);
            }else {
                answerMapper.correctByTeacher(integer,sid,0);
            }
        }
        if(i == qid.size()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Paper> getAll(){
        return paperMapper.getAll();
    }

    @Override
    public boolean insertNewPaper(Paper paper){
        return paperMapper.insertNewPaper(paper);
    }

    @Override
    public boolean deletePaper(Integer pid){
        return paperMapper.deletePaper(pid) && paperMapper.deleteAnswer(pid);
    }

    @Override
    public List<Paper> searchPaper(String pname){
        return paperMapper.searchPaper(pname);
    }

    @Override
    public boolean isDone(Integer sid,Integer pid){
        return paperMapper.isDone(sid,pid);
    }

    @Override
    public boolean getDone(Integer sid,Integer pid){
        if(paperMapper.getDone(sid,pid) != 0){
            return true;
        }else{
            return false;
        }
    }
}
