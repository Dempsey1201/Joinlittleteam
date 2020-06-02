package com.example.javaee.controller.question;

import com.example.javaee.entity.question.Question1;
import com.example.javaee.service.question.QuestionService;
import com.example.javaee.utils.resultHander.CommonErrorEnum;
import com.example.javaee.utils.resultHander.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    @RequestMapping("/insertQuestion")
    public List<Integer> insertQuestion(@RequestBody Question1[] question1s){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < question1s.length;i++) {
            try {
                list.add(questionService.insertNewQuestion(question1s[i]));
            }catch (Exception e){
                list.add(-1);
            }
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/insertNewQuestion")
    public Object insertNewQuestion(String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question1 question1 = new Question1(question, oa, ob, oc, od, answer, qtype, author, subject);
        return new ResponseBean(true, questionService.insertNewQuestion(question1));

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
    @RequestMapping("/deleteQuestion")
    public boolean deleteQuestion(Integer qid){
        return questionService.deleteQuestion(qid);
    }

    @ResponseBody
    @RequestMapping("/searchSubject")
    public List<Question1> searchSubject(String subject){
        return questionService.searchSubject(subject);
    }

    @ResponseBody
    @RequestMapping("/searchQuestion")
    public List<Question1> searchQuestion(String question){
        return questionService.searchQuestion(question);
    }

    @ResponseBody
    @RequestMapping("/searchQuestionBySubject")
    public List<Question1> searchQuestionBySubject(String subject,String question){
        return questionService.searchQuestionBySubject(subject,question);
    }

    @ResponseBody
    @RequestMapping("/updateQuestion")
    public ResponseBean updateQuestion(String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject){
        Question1 question1 = new Question1(question,oa,ob,oc,od,answer,qtype,author,subject);
        if(questionService.updateQuestion(question1)){
            return new ResponseBean(true,CommonErrorEnum.SUCCESS_REQUEST);
        }else {
            return new ResponseBean(false,CommonErrorEnum.FAIL_GETDATA);
        }
    }

    @ResponseBody
    @RequestMapping("/getAnswer")
    public String getAnswer(Integer qid){
        return questionService.getAnswer(qid);
    }
}
