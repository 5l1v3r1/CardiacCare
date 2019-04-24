package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAppointmentFragment extends Fragment {

    TextView aptgetdocname, aptdateget, apttimeget;
    ImageView btnaptget;

    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_viewappointments,null);

    firebaseAuth = FirebaseAuth.getInstance();

    aptgetdocname = (TextView) v.findViewById(R.id.aptgetdocname);
    aptdateget = (TextView) v.findViewById(R.id.aptdateget);
    apttimeget = (TextView) v.findViewById(R.id.apttimeget);
    btnaptget = (ImageView) v.findViewById(R.id.btnaptget);


        String u_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference dbgetdocname= FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("AppointmentDoctorName");

        dbgetdocname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String getdocName = String.valueOf(dataSnapshot.getValue());
                if(getdocName.equals("null")) {
                    aptgetdocname.setText("");
                }
                else {
                    aptgetdocname.setText(getdocName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        DatabaseReference dbgetdate= FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("AppointmentDate");

        dbgetdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String getdate = String.valueOf(dataSnapshot.getValue());
                if(getdate.equals("null")) {
                    aptdateget.setText("");
                }else {
                    aptdateget.setText(getdate);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference dbgettime = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("AppointmentTime");

        dbgettime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String gettime = String.valueOf(dataSnapshot.getValue());

                if(gettime.equals("null"))
                {
                    apttimeget.setText("");
                }
                else{
                    apttimeget.setText(gettime);
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

        btnaptget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Are You Sure You Want to delete this Appointment?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String u_id = firebaseAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id);

                                current_user_db.child("AppointmentDoctorName").setValue("");
                                current_user_db.child("AppointmentDate").setValue("");
                                current_user_db.child("AppointmentTime").setValue("");
                                Toast.makeText(getContext(), "Appointment Deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Appointment Not Deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();


            }
        });


    }

}
