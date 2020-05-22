package com.example.javaee.entity.classroom;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 20:31 2020/2/25
 */
public class Classroom {

    int id;
    String classname;
    String classno;
    String college;
    String major;
    int teacherno;
    String teachername;
    String sid;
    String paperno;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(int teacherno) {
        this.teacherno = teacherno;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPaperno() {
        return paperno;
    }

    public void setPaperno(String paperno) {
        this.paperno = paperno;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", classno='" + classno + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", teacherno=" + teacherno +
                ", teachername='" + teachername + '\'' +
                ", sid='" + sid + '\'' +
                ", paperno='" + paperno + '\'' +
                '}';
    }
}
