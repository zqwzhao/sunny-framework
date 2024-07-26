package com.itranswarp.jdbc.without.tx;


public class User {
    public int id;
    public String name;
    public Integer theAge;

    public void setAge(Integer age) {
        this.theAge = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTheAge() {
        return theAge;
    }

    public void setTheAge(Integer theAge) {
        this.theAge = theAge;
    }
}