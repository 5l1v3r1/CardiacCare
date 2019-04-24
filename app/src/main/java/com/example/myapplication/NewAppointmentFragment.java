package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class NewAppointmentFragment extends Fragment {
    public static final String DOC_Name = "Name";


    ListView listdoc;
    FirebaseAuth firebaseAuth;

    List<Docs> docs;

    DatabaseReference databaseDocs;
    DatabaseReference databaseFilterDocs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_newappointment,null);



    databaseDocs = FirebaseDatabase.getInstance().getReference("Users");
    String u_id = FirebaseAuth.getInstance().getUid();
    databaseFilterDocs = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Type");


    listdoc = (ListView) v.findViewById(R.id.docaptview);


    docs = new ArrayList<>();

    listdoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Docs DocList = docs.get(position);

            Intent intent = new Intent(getActivity().getApplicationContext(), addappointmentnew.class);

            intent.putExtra(DOC_Name, DocList.getName());

            startActivity(intent);

        }
    });


              databaseDocs.orderByChild("Type").equalTo("Doctor").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        Docs doc1 = postSnapshot.getValue(Docs.class);
                                        docs.add(doc1);

                                    }

                                    if (getActivity() != null) {

                                        DocList DocAdapter = new DocList(getActivity(), docs);
                                        listdoc.setAdapter(DocAdapter);

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {


                                }
                            });


            return v;

      }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}
