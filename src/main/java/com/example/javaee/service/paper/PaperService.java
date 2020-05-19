package com.example.javaee.service.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question;
import com.example.javaee.entity.utilClass.UtilClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
    boolean correctByTeacher(Integer qid,Integer sid,Integer getscore);
}
