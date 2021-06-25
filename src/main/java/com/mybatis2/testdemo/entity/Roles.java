package com.mybatis2.testdemo.entity;

import java.util.List;

public class Roles {

    private Integer id;
    private String role;


    public Roles(Integer id, String role) {
        this.id = id;
        this.role = role;

    }

    public Roles() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
