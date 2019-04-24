package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Docs {
    private String Name;
    private String Age;


    public Docs(){

    }

    public Docs(String Name, String Type, String Age) {
        this.Name = Name;
        this.Age = Age;
    }
    public String getName() {
        return Name;
    }


    public String getAge() {
        return Age;
    }
}
