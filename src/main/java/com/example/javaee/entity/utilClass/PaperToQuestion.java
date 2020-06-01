package com.example.javaee.entity.utilClass;

/**
 * author dongml
 * time 28/5/2020
 * description
 */
public class PaperToQuestion {
    private Integer pid;

    private Integer no;

    private Integer qid;

    private Integer qscore;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getQscore() {
        return qscore;
    }

    public void setQscore(Integer qscore) {
        this.qscore = qscore;
    }

    @Override
    public String toString() {
        return "PaperToQuestion{" +
                "pid=" + pid +
                ", no=" + no +
                ", qid=" + qid +
                ", qscore=" + qscore +
                '}';
    }
}
