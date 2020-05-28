package com.example.javaee.entity.utilClass;

/**
 * author dongml
 * time 28/5/2020
 * description
 */
public class StorePaper {
    private Integer pid;

    private Integer sid;

    private Integer qid;

    private String answer;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "StorePaper{" +
                "pid=" + pid +
                ", sid=" + sid +
                ", qid=" + qid +
                ", answer='" + answer + '\'' +
                '}';
    }
}
