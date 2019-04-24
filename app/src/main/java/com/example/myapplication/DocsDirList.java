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


public class DocsDirList extends ArrayAdapter<DocsDir> {
    private Activity context;
    List<DocsDir> docsdir;

    public DocsDirList(Activity context, List<DocsDir> docsdir) {
        super(context, R.layout.activity_docdirlist, docsdir);
        this.context = context;
        this.docsdir = docsdir;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_docdirlist, null, true);

        TextView docdirlistName = (TextView) listViewItem.findViewById(R.id.docdirlistName);
        TextView docdirlistType = (TextView) listViewItem.findViewById(R.id.docdirlistType);
        TextView docdirlistAge = (TextView) listViewItem.findViewById(R.id.docdirlistAge);
        TextView docdirlistMob = (TextView) listViewItem.findViewById(R.id.docdirlistmob);


        DocsDir doc1 = docsdir.get(position);
        docdirlistName.setText(doc1.getName());
        docdirlistType.setText(doc1.getType());
        docdirlistAge.setText(doc1.getAge());
        docdirlistMob.setText(doc1.getMobile());


        return listViewItem;
    }
}