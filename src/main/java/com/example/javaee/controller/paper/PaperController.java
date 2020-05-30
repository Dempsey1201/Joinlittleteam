package com.example.javaee.controller.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.question.Question1;
import com.example.javaee.entity.utilClass.PaperToQuestion;
import com.example.javaee.entity.utilClass.StorePaper;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author dongml
 * time 2020/4/12
 * description
 */
@CrossOrigin
@Controller
@RequestMapping("/paper")
public class PaperController {

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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

    @ResponseBody
    @RequestMapping("/getPaperByClass2")
    public List<Paper> getPaperByClass2(Integer sid,Integer classno){
        return paperService.getPaperByClass2(sid,classno);
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
    boolean storeAnswerAndJudge(@RequestBody StorePaper[] storePaper) {
        return paperService.isDone(storePaper[0].getSid(), storePaper[0].getPid()) && paperService.storeAnswer(storePaper);
    }

    @ResponseBody
    @RequestMapping("/getAllPaper")
    public List<Paper> getAll() {
        return paperService.getAll();
    }

    @ResponseBody
    @RequestMapping("/insertNewPaper")
    Integer insertNewPaper(String pname, Integer share, String teacher, String classno, Date start_time, Date end_time,
                           Double last_time, Integer full_score) {
        String subject = "";
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
    public boolean insertQuestionToPaper(@RequestBody PaperToQuestion[] paperToQuestions){
        List<Integer> pid = new ArrayList<>();
        List<Integer> no = new ArrayList<>();
        List<Integer> qid = new ArrayList<>();
        List<Integer> qscore = new ArrayList<>();
        for(int i = 0;i < paperToQuestions.length;i++){
            pid.add(paperToQuestions[i].getPid());
            no.add(paperToQuestions[i].getNo());
            qscore.add(paperToQuestions[i].getQscore());
            qid.add(paperToQuestions[i].getQid());
            System.out.println(qscore);
            System.out.println("-----------------");
            System.out.println(qid);
        }
        return paperService.insertQuestionToPaper(pid,no,qid,qscore);
    }

    @ResponseBody
    @RequestMapping("/reusePaper")
    public boolean reusePaper(Integer pid,Integer pid1) {
        return paperService.reusePaper(pid,pid1);
    }

    @ResponseBody
    @RequestMapping("/getScoreByPaper")
    public List<UtilClass> getScoreByPaper(Integer pid,Integer classno){
        return paperService.getScoreByPaper(pid,classno);
    }

    @ResponseBody
    @RequestMapping("/getPaperByNo")
    public Map<String,Object> getPaperByNo(Integer sid){
        return paperService.getPaperByNo(sid);
    }

    @ResponseBody
    @RequestMapping("/getPaperById")
    public List<Paper> getPaperById(Integer pid){
        return paperService.getPaperById(pid);
    }
}