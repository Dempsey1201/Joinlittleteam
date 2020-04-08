package com.example.javaee.entity.feelback;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 18:37 2019/12/18
 */
public class FeelBack {

    int id;
    int stu_id;//学生card
    String message;//信息
    String creat_time;//创建时间
    int status;//状态

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }


    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    @Override
    public String toString() {
        return "FeelBack{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", message='" + message + '\'' +
                ", creat_time='" + creat_time + '\'' +
                ", status=" + status +
                '}';
    }
}
