package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DocAppointmentsFragment extends Fragment {

    FirebaseAuth firebaseAuth;

    String settext,doctorfetch;

    ListView listpatientapt;

    List<pats2> pats2;


    DatabaseReference databasePats;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_appointments,null);


        firebaseAuth = FirebaseAuth.getInstance();

        listpatientapt = (ListView) v.findViewById(R.id.listpatientapts);


        pats2 = new ArrayList<>();

        /*
        listpatientapt.setOnItemClickListener(new firebaseAdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pats PatList = pats.get(position);
            }
        });
        */

        String u_id = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference testdb = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Name");

        try {

            testdb.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    settext = String.valueOf(dataSnapshot.getValue());
                    databasePats = FirebaseDatabase.getInstance().getReference("Users");


                    databasePats.orderByChild("AppointmentDoctorName").equalTo(settext).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            pats2.clear();
                            for (DataSnapshot postSnapshot1 : dataSnapshot1.getChildren()) {
                                pats2 pat1 = postSnapshot1.getValue(pats2.class);
                                pats2.add(pat1);

                            }

                            if (getActivity() != null) {
                                PatList2 PatAdapter = new PatList2(getActivity(), pats2);
                                Log.e("Doc Context", getActivity().toString());
                                listpatientapt.setAdapter(PatAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        catch (NullPointerException e){

        }



        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
