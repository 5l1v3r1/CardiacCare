package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class PatsDirList extends ArrayAdapter<PatsDir> {
    private Activity context;
    List<PatsDir> patsdir;

    public PatsDirList(Activity context, List<PatsDir> patsdir) {
        super(context, R.layout.activity_patdirlist, patsdir);
        this.context = context;
        this.patsdir = patsdir;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_patdirlist, null, true);

        TextView patdirlistName = (TextView) listViewItem.findViewById(R.id.patdirlistName);

        TextView patdirlistMob = (TextView) listViewItem.findViewById(R.id.patdirlistmob);


        PatsDir pat1 = patsdir.get(position);
        patdirlistName.setText(pat1.getName());

        patdirlistMob.setText(pat1.getMobile());


        return listViewItem;
    }
}