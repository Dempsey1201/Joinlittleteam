package com.example.javaee.entity.report;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:39 2019/12/5
 */
public class Report {

    int id;
    int stu_id;
    String class_name;
    String teacher;
    String score;//成绩
    String credit;//学分
    String point;//绩点

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", class_name='" + class_name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", score='" + score + '\'' +
                ", credit='" + credit + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}


