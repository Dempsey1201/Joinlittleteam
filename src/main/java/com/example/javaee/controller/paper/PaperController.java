package com.example.javaee.controller.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question1;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * author dongml
 * time 2020/4/12
 * description
 */
@CrossOrigin
@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    PaperService paperService;

    @ResponseBody
    @RequestMapping("/getScore")
    public Integer getScore(Integer sid, Integer pid) {
        return paperService.getScore(sid, pid);
    }

    @ResponseBody
    @RequestMapping("/getScoreByUser")
    public List<UtilClass> getScoreByUser(Integer sid) {
        return paperService.getScoreByUser(sid);
    }

    @ResponseBody
    @RequestMapping("/getClassAnswer")
    public List<UtilClass> getClassAnswer(Integer sid, Integer qid, Integer pid) {
        return paperService.getClassAnswer(sid, qid, pid);
    }

    @ResponseBody
    @RequestMapping("/getPaperByClass")
    public List<Paper> getPaperByClass(Integer classno) {
        return paperService.getPaperByClass(classno);
    }

    /**
     * @param sid
     * @param qid
     * @param answer
     * @return false寓意已经存在答案，不允许修改，成功即为true
     */
    @ResponseBody
    @RequestMapping("/insertAnswer")
    public boolean insertAnswer(Integer sid, Integer qid, String answer) {
        return paperService.insertAnswer(sid, qid, answer);
    }

    @ResponseBody
    @RequestMapping("/correctByTeacher")
    boolean correctByTeacher(Integer pid, Integer qid, Integer sid, Integer getscore) {
        return paperService.correctByTeacher(pid, qid, sid, getscore);
    }

    @ResponseBody
    @RequestMapping("/storeAnswerAndJudge")
    boolean storeAnswerAndJudge(Integer pid, @RequestParam(value = "", required = true) List<Integer> qid, Integer sid, @RequestParam(value = "", required = true) List<String> answer) {
        return paperService.storeAnswer(pid, qid, sid, answer) && paperService.isDone(sid, pid);
    }

    @ResponseBody
    @RequestMapping("/getAllPaper")
    public List<Paper> getAll() {
        return paperService.getAll();
    }

    @ResponseBody
    @RequestMapping("/insertNewPaper")
    boolean insertNewPaper(String pname, Integer share, String teacher, String classno, Date start_time, Date end_time,
                           Double last_time, Integer full_score, String subject) {
        Paper paper = new Paper(pname, share, teacher, classno, start_time, end_time, last_time, full_score, subject);
        return paperService.insertNewPaper(paper);
    }

    @ResponseBody
    @RequestMapping("/deletePaper")
    boolean deletePaper(Integer pid) {
        return paperService.deletePaper(pid);
    }

    @ResponseBody
    @RequestMapping("/searchPaper")
    List<Paper> searchPaper(String pname) {
        return paperService.searchPaper(pname);
    }

    @ResponseBody
    @RequestMapping("/getDone")
    boolean getDone(Integer sid, Integer pid) {
        return paperService.getDone(sid, pid);
    }

    @ResponseBody
    @RequestMapping("/updateUserScore")
    boolean updateUserScore(Integer sid, Integer pid, Integer score) {
        return paperService.updateUserScore(pid, sid, score);
    }

    @ResponseBody
    @RequestMapping("/getPaperQuestion")
    public List<Question1> getPaperQuestion(Integer pid) {
        return paperService.getPaperQuestion(pid);
    }

    @ResponseBody
    @RequestMapping("/insertQuestionToPaper")
    public boolean insertQuestionToPaper(List<Integer> pid,List<Integer> no,List<Integer> qid,List<Integer> qscore){
        return paperService.insertQuestionToPaper(pid,no,qid,qscore);
    }

    @ResponseBody
    @RequestMapping("/reusePaper")
    public boolean reusePaper(Integer pid) {
        return paperService.reusePaper(pid);
    }

    @ResponseBody
    @RequestMapping("/getScoreByPaper")
    public List<Integer> getScoreByPaper(Integer pid,Integer classno){
        return paperService.getScoreByPaper(pid,classno);
    }
}