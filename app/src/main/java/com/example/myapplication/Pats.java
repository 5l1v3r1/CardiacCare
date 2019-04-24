package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pats {
    private String Name;
    private String AppointmentDate;
    private String AppointmentTime;


    public Pats(){

    }

    public Pats(String Name, String AppointmentDate, String AppointmentTime) {
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
