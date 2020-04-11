package com.example.javaee.mapper.question;

import java.util.Objects;

/**
 * author dongml
 * time 2020/4/11
 * description
 */
public class QuestionMapper {

    int pid;

    String question;

    String a;

    String b;

    String c;

    String d;

    String answer;

    int type;

    String author;

    String subject;

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
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        if (!(o instanceof QuestionMapper)) return false;
        QuestionMapper that = (QuestionMapper) o;
        return pid == that.pid &&
                type == that.type &&
                Objects.equals(question, that.question) &&
                Objects.equals(a, that.a) &&
                Objects.equals(b, that.b) &&
                Objects.equals(c, that.c) &&
                Objects.equals(d, that.d) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(author, that.author) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, question, a, b, c, d, answer, type, author, subject);
    }

    @Override
    public String toString() {
        return "QuestionMapper{" +
                "pid=" + pid +
                ", question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + type +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
