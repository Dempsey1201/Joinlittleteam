package com.example.javaee.controller.question;

import com.example.javaee.entity.question.Question;
import com.example.javaee.service.question.QuestionService;
import com.example.javaee.utils.resultHander.CommonErrorEnum;
import com.example.javaee.utils.resultHander.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */
@CrossOrigin
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping("/getAllQuestion")
    public Object getAll() throws Exception {

        return questionService.getAll();
    }

    @ResponseBody
    @RequestMapping("/insertNewQuestion")
    public Object insertNewQuestion(String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question1 = new Question(question, oa, ob, oc, od, answer, qtype, author, subject);
        if (questionService.insertNewQuestion(question1)) {
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_REQUEST);
        } else {
            return new ResponseBean(false, CommonErrorEnum.DATABASE_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping("/firstJudge")
    public boolean firstJudge(String answer, Integer qid) {
        return questionService.firstJudge(answer,qid);
    }

    @ResponseBody
    @RequestMapping("/secondJudge")
    public List<String> secondJudge(String answer, Integer qid) {
        return questionService.secondJudge(answer, qid);
    }

    @ResponseBody
    @RequestMapping
    public boolean deleteQuestion(Integer qid){
        Question question = new Question();
        question.setPid(qid);
        return questionService.deleteQuestion(question);
    }
}
