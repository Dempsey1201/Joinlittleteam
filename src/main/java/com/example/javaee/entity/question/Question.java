package com.example.javaee.entity.question;

import java.util.Objects;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
public class Question {

    int pid;

    String question;

    String oa;

    String ob;

    String oc;

    String od;

    String answer;

    int qtype;

    String author;

    String subject;

    public Question(String question, String oa, String ob, String oc, String od, String answer, int qtype, String author, String subject) {
        this.question = question;
        this.oa = oa;
        this.ob = ob;
        this.oc = oc;
        this.od = od;
        this.answer = answer;
        this.qtype = qtype;
        this.author = author;
        this.subject = subject;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return oa;
    }

    public void setA(String a) {
        this.oa = a;
    }

    public String getB() {
        return ob;
    }

    public void setB(String b) {
        this.ob = b;
    }

    public String getC() {
        return oc;
    }

    public void setC(String c) {
        this.oc = c;
    }

    public String getD() {
        return od;
    }

    public void setD(String d) {
        this.od = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getType() {
        return qtype;
    }

    public void setType(int type) {
        this.qtype = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return pid == question1.pid &&
                qtype == question1.qtype &&
                Objects.equals(question, question1.question) &&
                Objects.equals(oa, question1.oa) &&
                Objects.equals(ob, question1.ob) &&
                Objects.equals(oc, question1.oc) &&
                Objects.equals(od, question1.od) &&
                Objects.equals(answer, question1.answer) &&
                Objects.equals(author, question1.author) &&
                Objects.equals(subject, question1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, question, oa, ob, oc, od, answer, qtype, author, subject);
    }

    @Override
    public String toString() {
        return "QuestionMapper{" +
                "pid=" + pid +
                ", question='" + question + '\'' +
                ", a='" + oa + '\'' +
                ", b='" + ob + '\'' +
                ", c='" + oc + '\'' +
                ", d='" + od + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + qtype +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
