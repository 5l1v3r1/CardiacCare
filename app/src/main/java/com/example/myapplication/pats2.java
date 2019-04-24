package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class pats2 {
    private String Name;
    private String AppointmentDate;
    private String AppointmentTime;


    public pats2(){

    }

    public pats2(String Name, String AppointmentDate, String AppointmentTime) {
        this.Name = Name;
        this.AppointmentDate = AppointmentDate;
        this.AppointmentTime = AppointmentTime;
    }
    public String getName() {
        return Name;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }
}
