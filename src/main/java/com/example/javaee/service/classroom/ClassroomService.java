package com.example.javaee.service.classroom;

import com.example.javaee.entity.classroom.Classroom;


import java.util.List;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:53 2020/4/11
 */
public interface ClassroomService {
    public List<Classroom> list()throws Exception;
    public int addClassroom(Classroom classroom)throws Exception;
    public int deleteClassroom(int id)throws Exception;
    public int update(Classroom classroom) throws Exception;
    public int joinClassroom(int id,int cid) throws Exception;
    public int outClassroom(int id,int cid) throws Exception;
    public Classroom query(int id) throws Exception;
    public Classroom queryClassroom(String str) throws Exception;
}
