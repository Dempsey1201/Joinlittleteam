package com.example.javaee.entity.classroom;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 20:31 2020/2/25
 */
public class Classroom {

    int id;
    String classname;
    String college;
    int teacherno;
    String teachername;
    String studentno;
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

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getPaperno() {
        return paperno;
    }

    public void setPaperno(String paperno) {
        this.paperno = paperno;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", college='" + college + '\'' +
                ", teacherno=" + teacherno +
                ", teachername='" + teachername + '\'' +
                ", studentno='" + studentno + '\'' +
                ", paperno='" + paperno + '\'' +
                '}';
    }
}
