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

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String headUrl) {
        HeadUrl = headUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teachername='" + teachername + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", classno='" + classno + '\'' +
                ", HeadUrl='" + HeadUrl + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }
}
