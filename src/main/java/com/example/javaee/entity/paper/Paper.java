package com.example.javaee.entity.paper;

import java.util.Date;
import java.util.Objects;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
public class Paper {

    int qid;

    String exam_name;

    int share;

    String teacher;

    String classno;

    Date start_time;

    Date end_time;

    Date last_time;
    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getLast_time() {
        return last_time;
    }

    public void setLast_time(Date last_time) {
        this.last_time = last_time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paper)) return false;
        Paper paper = (Paper) o;
        return qid == paper.qid &&
                share == paper.share &&
                Objects.equals(exam_name, paper.exam_name) &&
                Objects.equals(teacher, paper.teacher) &&
                Objects.equals(classno, paper.classno) &&
                Objects.equals(start_time, paper.start_time) &&
                Objects.equals(end_time, paper.end_time) &&
                Objects.equals(last_time, paper.last_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qid, exam_name, share, teacher, classno, start_time, end_time, last_time);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "qid=" + qid +
                ", exam_name='" + exam_name + '\'' +
                ", share=" + share +
                ", teacher='" + teacher + '\'' +
                ", classno='" + classno + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", last_time=" + last_time +
                '}';
    }
}
