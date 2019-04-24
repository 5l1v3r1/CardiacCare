package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class DocList extends ArrayAdapter<Docs> {
    private Activity context;
    List<Docs> docs;

    public DocList(Activity context, List<Docs> docs) {
        super(context, R.layout.activity_doclist, docs);
        this.context = context;
        this.docs = docs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_doclist, null, true);

        TextView doclistName = (TextView) listViewItem.findViewById(R.id.doclistName);
        TextView doclistAge = (TextView) listViewItem.findViewById(R.id.doclistAge);

        Docs doc1 = docs.get(position);
        doclistName.setText(doc1.getName());
        doclistAge.setText(doc1.getAge());


        return listViewItem;
    }
}