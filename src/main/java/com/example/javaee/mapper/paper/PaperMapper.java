package com.example.javaee.mapper.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
@Mapper
public interface PaperMapper {
    List<Paper> getAll();
    boolean insertNewPaper(Paper paper);
    boolean deletePaper(Integer pid);
    boolean deleteAnswer(Integer pid);
    List<Paper> searchPaper(String pname);
    boolean isDone(Integer sid,Integer pid);
    Integer getDone(Integer sid,Integer pid);
    List<Question1> getPaperQuestion(Integer pid);
    boolean insertQuestionToPaper(Integer pid,Integer no,Integer qid,Integer qscore);
    Integer selectMainKey();
}
