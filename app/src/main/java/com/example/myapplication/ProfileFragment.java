package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewProfileBMI, textViewProfileGender, textViewMobile, textViewEmail, textViewProfileBMIStatus;

    private FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,null);

        textViewName = (TextView) v.findViewById(R.id.textViewProfileName);
        textViewAge = (TextView) v.findViewById(R.id.textViewProfileAge);
        textViewProfileBMI = (TextView) v.findViewById(R.id.textViewProfileBMI);
        textViewProfileGender = (TextView) v.findViewById(R.id.textViewProfileGender);
        textViewMobile = (TextView) v.findViewById(R.id.textViewProfileMob);
        textViewEmail = (TextView) v.findViewById(R.id.textViewProfileEmail);
        textViewProfileBMIStatus = (TextView) v.findViewById(R.id.textViewProfileBMIStatus);

        firebaseAuth = firebaseAuth.getInstance();

        FirebaseUser useremail = firebaseAuth.getCurrentUser();
        final String email = useremail.getEmail();

        textViewEmail.setText(email);

        String u_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Name");
        DatabaseReference currrent_user_db2 = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Age");
        DatabaseReference currrent_user_db3 = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("BMI");
        DatabaseReference dbref_gender = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Gender");
        DatabaseReference dbref_mob = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Mobile");
        DatabaseReference dbref_bmistatus = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("BMI_Status");
        currrent_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childName = String.valueOf(dataSnapshot.getValue());
                textViewName.setText(childName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currrent_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childAge = String.valueOf(dataSnapshot.getValue());
                textViewAge.setText(childAge);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currrent_user_db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childBMI = String.valueOf(dataSnapshot.getValue());
                if(childBMI.equals("null")) {
                    textViewProfileBMI.setText("");
                }else {
                    textViewProfileBMI.setText(childBMI);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbref_gender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childGender = String.valueOf(dataSnapshot.getValue());
                textViewProfileGender.setText(childGender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbref_mob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childMob = String.valueOf(dataSnapshot.getValue());
                textViewMobile.setText(childMob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbref_bmistatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childBMIStatus = String.valueOf(dataSnapshot.getValue());
                if(childBMIStatus.equals("null")) {
                    textViewProfileBMIStatus.setText("");
                }else {
                    textViewProfileBMIStatus.setText("(" + childBMIStatus + ")");
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
