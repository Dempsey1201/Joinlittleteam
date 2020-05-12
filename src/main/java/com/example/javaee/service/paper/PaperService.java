package com.example.javaee.service.paper;

import com.example.javaee.entity.question.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author dongml
 * time 2020/3/12
 * description
 */

public interface PaperService {
    Integer getScore(Integer sid,Integer pid);

}
