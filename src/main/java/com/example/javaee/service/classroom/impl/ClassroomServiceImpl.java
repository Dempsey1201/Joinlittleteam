package com.example.javaee.service.classroom.impl;

import com.example.javaee.entity.classroom.Classroom;
import com.example.javaee.mapper.classroom.ClassroomMapper;
import com.example.javaee.service.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:53 2020/4/11
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    ClassroomMapper classroomMapper;

    @Override
    public List<Classroom> list()throws Exception{
        return classroomMapper.listClassroom();
    }

    @Override
    public int addClassroom(Classroom classroom)throws Exception{
        return classroomMapper.addClassroom(classroom);
    }

    @Override
    public int deleteClassroom(int id)throws Exception{
        return classroomMapper.deleteClassroom(id);
    }

    @Override
    public int update(Classroom classroom) throws Exception{
        return classroomMapper.update(classroom);
    }


}
