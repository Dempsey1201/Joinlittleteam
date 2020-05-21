package com.example.javaee.entity.paper;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
public class Paper {

    int pid;

    String pname;

    int share;

    String teacher;

    String classno;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date start_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date end_time;

    Double last_time;

    Integer full_score;

    String subject;

    public Paper(){

    }

    public Paper(String pname, int share, String teacher, String classno, Date start_time, Date end_time, Double last_time, Integer full_score, String subject) {
        this.pname = pname;
        this.share = share;
        this.teacher = teacher;
        this.classno = classno;
        this.start_time = start_time;
        this.end_time = end_time;
        this.last_time = last_time;
        this.full_score = full_score;
        this.subject = subject;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String exam_name) {
        this.pname = exam_name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int qid) {
        this.pid = qid;
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

    public Double getLast_time() {
        return last_time;
    }

    public void setLast_time(Double last_time) {
        this.last_time = last_time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paper)) return false;
        Paper paper = (Paper) o;
        return pid == paper.pid &&
                share == paper.share &&
                Objects.equals(pname, paper.pname) &&
                Objects.equals(teacher, paper.teacher) &&
                Objects.equals(classno, paper.classno) &&
                Objects.equals(start_time, paper.start_time) &&
                Objects.equals(end_time, paper.end_time) &&
                Objects.equals(last_time, paper.last_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, pname, share, teacher, classno, start_time, end_time, last_time);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "qid=" + pid +
                ", exam_name='" + pname + '\'' +
                ", share=" + share +
                ", teacher='" + teacher + '\'' +
                ", classno='" + classno + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", last_time=" + last_time +
                '}';
    }
}
