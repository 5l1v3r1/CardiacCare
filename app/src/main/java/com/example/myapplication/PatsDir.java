package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class PatsDir {
    private String Name;

    private String Mobile;


    public PatsDir(){

    }

    public PatsDir(String Name, String Mobile) {
        this.Name = Name;

        this.Mobile = Mobile;
    }

    public String getName() {return Name;}

    public String getMobile() {return Mobile; }

    }

