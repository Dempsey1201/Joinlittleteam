package com.example.javaee.controller.paper;

import com.example.javaee.entity.paper.Paper;
import com.example.javaee.entity.utilClass.UtilClass;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Integer getScore(Integer sid,Integer pid){
        return paperService.getScore(sid,pid);
    }

    @ResponseBody
    @RequestMapping("/getScoreByUser")
    public List<UtilClass>getScoreByUser(Integer sid){
        return paperService.getScoreByUser(sid);
    }

    @ResponseBody
    @RequestMapping("/getClassAnswer")
    public List<UtilClass> getClassAnswer(Integer sid,Integer qid,Integer pid){
        return paperService.getClassAnswer(sid,qid,pid);
    }

    @ResponseBody
    @RequestMapping("/getPaperByClass")
    public List<Paper> getPaperByClass(Integer classno){
        return paperService.getPaperByClass(classno);
    }

    /**
     *
     * @param sid
     * @param qid
     * @param answer
     * @return false寓意已经存在答案，不允许修改，成功即为true
     */
    @ResponseBody
    @RequestMapping("/insertAnswer")
    public boolean insertAnswer(Integer sid,Integer qid,String answer){
        return paperService.insertAnswer(sid,qid,answer);
    }

    @ResponseBody
    @RequestMapping("/correctByTeacher")
    boolean correctByTeacher(Integer qid,Integer sid,Integer getscore){
        return paperService.correctByTeacher(qid, sid, getscore);
    }
}
