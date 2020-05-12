package com.example.javaee.service.paper.impl;

import com.example.javaee.mapper.paper.PaperMapper;
import com.example.javaee.mapper.scoreMapper.ScoreMapper;
import com.example.javaee.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Integer getScore(Integer sid,Integer pid){
        return scoreMapper.getScore(sid,pid);
    }


}
