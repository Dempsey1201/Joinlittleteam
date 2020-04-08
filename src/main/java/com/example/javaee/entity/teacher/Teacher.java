package com.example.javaee.entity.teacher;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 20:50 2020/2/25
 */
public class Teacher {

    int id;
    String teachername;
    String email;
    String password;
    String sex;
    String college;
    String classno;
    String HeadUrl;
    String path;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_card() {
        return teacher_card;
    }

    public void setTeacher_card(String teacher_card) {
        this.teacher_card = teacher_card;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}
