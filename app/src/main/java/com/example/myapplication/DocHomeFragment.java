package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DocHomeFragment extends Fragment {

    FirebaseAuth firebaseAuth;

    String settext,doctorfetch;

    ListView listpatientapt2;

    List<Pats> pats;

    DatabaseReference databasePats;


    ViewFlipper v_flipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_home,null);

        int images[] = {R.drawable.cardiac1,R.drawable.cardiac2,R.drawable.cardiac3};

        v_flipper = v.findViewById(R.id.viewflipper2);


        firebaseAuth = FirebaseAuth.getInstance();

        listpatientapt2 = (ListView) v.findViewById(R.id.listpatientapts2);


        pats = new ArrayList<>();

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
                            pats.clear();
                            for (DataSnapshot postSnapshot1 : dataSnapshot1.getChildren()) {
                                Pats pat1 = postSnapshot1.getValue(Pats.class);
                                pats.add(pat1);

                            }

                            if (getActivity() != null) {
                                PatList PatAdapter = new PatList(getActivity(), pats);
                                Log.e("Doc Context", getActivity().toString());
                                listpatientapt2.setAdapter(PatAdapter);
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







/*
        for(int i=0; i< images.length; i++){
            flipperImages(images[i]);
        }
*/

        for(int image: images){
            flipperImages(image);
        }
        return v;


    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
