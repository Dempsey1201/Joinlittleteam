package com.example.javaee.service.paper.impl;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question1;
import com.example.javaee.entity.user.User;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.mapper.answer.AnswerMapper;
import com.example.javaee.mapper.paper.PaperMapper;
import com.example.javaee.mapper.question.QuestionMapper;
import com.example.javaee.mapper.scoreMapper.ScoreMapper;
import com.example.javaee.service.paper.PaperService;
import com.example.javaee.service.user.UserService;
//import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public boolean insertQuestionToPaper(List<Integer> pid,List<Integer> no,List<Integer> qid,List<Integer> qscore){
        int i = 0;
        for(i=0;i < pid.size();i++){
            Integer integer = pid.get(i);
            Integer integer1 = no.get(i);
            Integer integer2 = qid.get(i);
            Integer integer3 = qscore.get(i);
            paperMapper.insertQuestionToPaper(integer,integer1,integer2,integer3);
        }
        if (i == pid.size()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean reusePaper(Integer pid){
        List<Question1> question = paperMapper.getPaperQuestion(pid);
        List<Integer> no = new ArrayList<>(),qid = new ArrayList<>(),qscore = new ArrayList<>(),pid1 = new ArrayList<>();
        Integer pid2 = paperMapper.selectMainKey();
        int i = 0;
        if(question.size() == 0){
            return false;
        }
        for (i = 0;i <question.size();i++){
            Question1 question1 = question.get(i);
            pid1.add(pid2);
            no.add(i+1);
            qid.add(question1.getQid());
            qscore.add(question1.getQscore());
        }
        if(insertQuestionToPaper(pid1,no,qid,qscore)){
            return true;
        }else {
            return false;
        }
    }

    @Autowired
    UserService userService;

    @Override
    public List<Integer> getScoreByPaper(Integer pid,Integer classno) {
        List<User> sid = new ArrayList<>();
        try {
            sid = userService.queryClass(classno);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Integer> score1 = new ArrayList<>();
        int i;
        for(i = 0;i < sid.size();i ++){
            score1.add(scoreMapper.getScore(sid.get(i).getId(),pid));
        }
        return score1;
    }

    @Override
    public boolean storeAnswer(Integer pid,List<Integer>qid,Integer sid,List<String> answer){
        int i;
        Integer all = 0;
        for(i = 0;i < qid.size();i++){
            Integer integer = qid.get(i);
            String string = answer.get(i);
            answerMapper.insertAnswer(sid,integer,string);
            if(questionMapper.getAnswer(integer).equals(string) ){
                Integer integer1 = scoreMapper.getQscore(integer,pid);
                all += integer1;
                answerMapper.correctByTeacher(integer,sid,integer1);
            }else {
                answerMapper.correctByTeacher(integer,sid,0);
            }
        }
        if(i == qid.size()){
            scoreMapper.insertUserScore(pid,sid,all);
            return true;
        }else {
            return false;
        }
    }

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
        if(answerMapper.getUserAnswer(sid,qid)!= null){
            return false;
        }
        return answerMapper.insertAnswer(sid,qid,answer);
    }

    @Override
    public boolean correctByTeacher(Integer pid,Integer qid,Integer sid,Integer getscore){
        Integer score = scoreMapper.getScore(sid,pid);
        return answerMapper.correctByTeacher(qid,sid,getscore) && scoreMapper.updateUserScore(pid,sid,score+getscore);
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

    @Override
    public boolean insertUserScore(Integer pid,Integer sid,Integer score){
        return scoreMapper.insertUserScore(pid,sid,score);
    }

    @Override
    public boolean updateUserScore(Integer pid,Integer sid,Integer score){
        return scoreMapper.updateUserScore(pid,sid,score);
    }

    @Override
    public List<Question1> getPaperQuestion(Integer pid){
        return paperMapper.getPaperQuestion(pid);
    }

}
