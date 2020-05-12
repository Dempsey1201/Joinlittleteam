package com.example.javaee.controller.paper;

import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author dongml
 * time 2020/4/12
 * description
 */
@CrossOrigin
@Controller
@RequestMapping("paper")
public class PaperController {
    @Autowired
    PaperService paperService;

    @ResponseBody
    @RequestMapping("getScore")
    public Integer getScore(Integer sid,Integer pid){
        return paperService.getScore(sid,pid);
    }
}
