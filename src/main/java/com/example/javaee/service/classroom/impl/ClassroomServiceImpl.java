package com.example.javaee.service.classroom.impl;

import com.example.javaee.entity.classroom.Classroom;
import com.example.javaee.entity.user.User;
import com.example.javaee.mapper.classroom.ClassroomMapper;
import com.example.javaee.service.classroom.ClassroomService;
import com.example.javaee.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    UserService userService;

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
    @Override
    public int joinClassroom(int id,int cid) throws Exception{
        User user=userService.queryUser(id);
        String str=user.getClassno();
        if(str==null){
            str=String.valueOf(cid);
        }
        else str=str+","+cid;
        Classroom classroom=classroomMapper.queryClassroom(cid);
        String idStr=classroom.getSid();
        if(idStr==null){
            idStr=String.valueOf(id);
        }
        else idStr=idStr+","+id;

        //String[] arr = str.split(",");
        return classroomMapper.join(idStr,cid)+classroomMapper.joinB(id,str);
    }
    @Override
    public int outClassroom(int id,int cid) throws Exception{
        User user=userService.queryUser(id);
        String str=user.getClassno();
        String[] arr = str.split(",");
        List<String> list= Arrays.asList(arr);
        List<String> arrList = new ArrayList<String>(list);
        arrList.remove(String.valueOf(cid));
        //String[]cidStr = (String[])arrList.ToArray(typeof(String)) ;
        //String.Join(",",(String[])list.ToArray(typeof( string)));
        //String cidStr=String.valueOf(arrList);
        String cidStr=String.join(",", arrList);

        Classroom classroom=classroomMapper.queryClassroom(cid);
        String idStr=classroom.getSid();
        //System.out.println(idStr);
        String[] idS=idStr.split(",");
        List<String> list1=Arrays.asList(idS);
        arrList=new ArrayList<String>(list1);
        arrList.remove(String.valueOf(id));
        //idStr=String.valueOf(arrList);
        idStr=String.join(",", arrList);

        System.out.println(cidStr+idStr);
        if (cidStr==""){
            cidStr=null;
        }
        if (idStr==""){
            idStr=null;
        }
        return classroomMapper.outB(id,cidStr)+classroomMapper.out(idStr,cid);
    }
    @Override
    public Classroom query(int id) throws Exception{
        return  classroomMapper.queryClassroom(id);
    }
    @Override
    public Classroom queryClassroom(String str) throws Exception{
        return classroomMapper.query(str);
    }

}
