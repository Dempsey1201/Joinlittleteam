package com.example.javaee.entity.question;

import java.util.Objects;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
public class Question1 {

    int  qid;

    String question;

    String oa;

    String ob;

    String oc;

    String od;

    String answer;

    int qtype;

    String author;

    String subject;

    public Question1() {
    }

    public Question1(String question, String oa, String ob, String oc, String od, String answer, int qtype, String author, String subject) {
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

    public String getOa() {
        return oa;
    }

    public void setOa(String oa) {
        this.oa = oa;
    }

    public String getOb() {
        return ob;
    }

    public void setOb(String ob) {
        this.ob = ob;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public int getQtype() {
        return qtype;
    }

    public void setQtype(int qtype) {
        this.qtype = qtype;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int pid) {
        this.qid = pid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
        if (!(o instanceof Question1)) return false;
        Question1 question1 = (Question1) o;
        return qid == question1.qid &&
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
        return Objects.hash(qid, question, oa, ob, oc, od, answer, qtype, author, subject);
    }

    @Override
    public String toString() {
        return "QuestionMapper{" +
                "pid=" + qid +
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
