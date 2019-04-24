package com.example.myapplication;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class DocProfileFragment extends Fragment {

    private TextView textViewDocName, textViewDocGender , textViewDocEmail, textViewDocMobile;
    private TextView textViewDocAge;
    private FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_profile,null);

        textViewDocName = (TextView) v.findViewById(R.id.textViewDocProfileName);
        textViewDocGender = (TextView) v.findViewById(R.id.textViewProfileDocGender);
        textViewDocAge = (TextView) v.findViewById(R.id.textViewProfileDocAge);
        textViewDocEmail = (TextView) v.findViewById(R.id.textViewProfileDocEmail);
        textViewDocMobile = (TextView) v.findViewById(R.id.textViewProfileDocMob);
        firebaseAuth = firebaseAuth.getInstance();


        FirebaseUser useremail = firebaseAuth.getCurrentUser();
        final String email = useremail.getEmail();

        textViewDocEmail.setText(email);


        String u_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Name");
        DatabaseReference currrent_user_db2 = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Age");
        DatabaseReference dbref_docgender = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Gender");
        DatabaseReference dbref_docmob = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Mobile");


        currrent_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childName = String.valueOf(dataSnapshot.getValue());
                textViewDocName.setText(childName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currrent_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childAge = String.valueOf(dataSnapshot.getValue());
                textViewDocAge.setText(childAge);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbref_docgender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childGender = String.valueOf(dataSnapshot.getValue());
                textViewDocGender.setText(childGender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbref_docmob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childMob = String.valueOf(dataSnapshot.getValue());
                textViewDocMobile.setText(childMob);
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
