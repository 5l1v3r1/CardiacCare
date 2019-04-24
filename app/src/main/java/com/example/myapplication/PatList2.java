package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class PatList2 extends ArrayAdapter<pats2> {
    private Activity context1;
    List<pats2> pats2;

    public PatList2(Activity context, List<pats2> pats2) {


        super(context, R.layout.activity_pat_list2, pats2);

        try {
            this.context1 = context;
            this.pats2 = pats2;
        }
        catch (NullPointerException e){

        }

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context1.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_pat_list2, null, true);

        TextView patlistname1 = (TextView) listViewItem.findViewById(R.id.patlistname1);
        TextView patlistdate1 = (TextView) listViewItem.findViewById(R.id.patlistdate1);
        TextView patlisttime1 = (TextView) listViewItem.findViewById(R.id.patlisttime1);

        pats2 pat2 = pats2.get(position);
        patlistname1.setText(pat2.getName());
        patlistdate1.setText(pat2.getAppointmentDate());
        patlisttime1.setText(pat2.getAppointmentTime());


        return listViewItem;
    }
}