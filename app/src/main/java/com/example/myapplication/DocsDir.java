package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DocsDir {
    private String Name;
    private String Type;
    private String Age;
    private String Mobile;


    public DocsDir(){

    }

    public DocsDir(String Name, String Type, String Age) {
        this.Name = Name;
        this.Type = Type;
        this.Age = Age;
        this.Mobile = Mobile;
    }
    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public String getAge() {
        return Age;
    }

    public String getMobile() {return Mobile;}

}
