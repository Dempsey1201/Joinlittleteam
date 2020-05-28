package com.example.javaee.service.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question1;
import com.example.javaee.entity.utilClass.StorePaper;
import com.example.javaee.entity.utilClass.UtilClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */

/**
 * 我把answer和score都写在这个service和controller里面的
 * */
public interface PaperService {
    Integer getScore(Integer sid,Integer pid);
    List<UtilClass> getScoreByUser(Integer sid);
    List<UtilClass> getClassAnswer(Integer sid,Integer qid,Integer pid);
    List<Paper> getPaperByClass(Integer classno);
    boolean insertAnswer(Integer sid,Integer qid,String answer);
    boolean correctByTeacher(Integer pid,Integer qid,Integer sid,Integer getscore);
    boolean storeAnswer(StorePaper[] storePaper);
    List<Paper> getAll();
    Integer insertNewPaper(Paper paper);
    boolean deletePaper(Integer pid);
    List<Paper> searchPaper(String pname);
    boolean isDone(Integer sid,Integer pid);
    boolean getDone(Integer sid,Integer pid);
    boolean insertUserScore(Integer pid,Integer sid,Integer score);
    boolean updateUserScore(Integer pid,Integer sid,Integer score);
    List<Question1> getPaperQuestion(Integer pid);
    boolean insertQuestionToPaper(List<Integer> pid,List<Integer> no,List<Integer> qid,List<Integer> qscore);
    boolean reusePaper(Integer pid,Integer pid1);
    List<UtilClass>  getScoreByPaper(Integer pid,Integer claano);
    List<Paper> getPaperByNo(Integer sid);
}
