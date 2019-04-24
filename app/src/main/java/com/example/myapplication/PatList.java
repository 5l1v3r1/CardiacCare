package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class PatList extends ArrayAdapter<Pats> {
    private Activity context1;
    List<Pats> pats;

    public PatList(Activity context, List<Pats> pats) {
        super(context, R.layout.activity_patlist, pats);

        try {
            this.context1 = context;
            this.pats = pats;
        }
        catch (NullPointerException e){

        }

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context1.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_patlist, null, true);

        TextView patlistname = (TextView) listViewItem.findViewById(R.id.patlistname);
        TextView patlistdate = (TextView) listViewItem.findViewById(R.id.patlistdate);
        TextView patlisttime = (TextView) listViewItem.findViewById(R.id.patlisttime);

        Pats pat1 = pats.get(position);
        patlistname.setText(pat1.getName());
        patlistdate.setText(pat1.getAppointmentDate());
        patlisttime.setText(pat1.getAppointmentTime());


        return listViewItem;
    }
}