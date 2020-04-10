package com.example.javaee.entity.admin;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 21:38 2019/12/3
 */
public class Admin {

    int id;
    String adminname;
    String card;
    String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminname='" + adminname + '\'' +
                ", card='" + card + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
