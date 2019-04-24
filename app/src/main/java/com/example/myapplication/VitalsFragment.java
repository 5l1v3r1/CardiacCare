package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VitalsFragment extends Fragment {

    private TextView Bloodsugar, BloodPressure, Weight, Height, BMI, BMIComment, TextViewBMI;
    private EditText editTextBloodSugar, editTextBloodPressure, editTextWeight, editTextHeight;
    private Button btnSaveVitals;

    private Button btnClearVitals;

    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vitals,null);

        firebaseAuth = FirebaseAuth.getInstance();


        Bloodsugar = (TextView) v.findViewById(R.id.textViewBloodSugar);
        BloodPressure = (TextView) v.findViewById(R.id.textViewBloodPressure);
        Weight = (TextView) v.findViewById(R.id.textViewWeight);
        Height = (TextView) v.findViewById(R.id.textViewHeight);
        BMI = (TextView) v.findViewById(R.id.textViewBMI);

        editTextBloodSugar = (EditText) v.findViewById(R.id.editTextBloodSugar);
        editTextBloodPressure = (EditText) v.findViewById(R.id.editTextBloodPressure);
        editTextWeight = (EditText) v.findViewById(R.id.editTextWeight);
        editTextHeight = (EditText) v.findViewById(R.id.editTextHeight);
        TextViewBMI = (TextView) v.findViewById(R.id.textViewVitalBMI);
        BMIComment = (TextView) v.findViewById(R.id.textViewBMIComment);

        btnSaveVitals = (Button) v.findViewById(R.id.btnSaveVitals);

        btnClearVitals = (Button) v.findViewById(R.id.btnClearVitals);


        btnSaveVitals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calculateBMI();

                String BloodSugarkey = Bloodsugar.getText().toString();
                String BloodPressurekey = BloodPressure.getText().toString();
                String Weightkey = Weight.getText().toString();
                String Heightkey = Height.getText().toString();
                String BMIkey = BMI.getText().toString();

                String BloodSugarvalue = editTextBloodSugar.getText().toString();
                String BloodPressurevalue = editTextBloodPressure.getText().toString();
                String Weightvalue = editTextWeight.getText().toString();
                String Heightvalue = editTextHeight.getText().toString();
                String BMIvalue = TextViewBMI.getText().toString();


                String user_id = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


                currrent_user_db.child(BloodSugarkey).setValue(BloodSugarvalue);
                currrent_user_db.child(BloodPressurekey).setValue(BloodPressurevalue);
                currrent_user_db.child(Weightkey).setValue(Weightvalue);
                currrent_user_db.child(Heightkey).setValue(Heightvalue);
                currrent_user_db.child(BMIkey).setValue(BMIvalue);



            }


        });


        btnClearVitals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calculateBMI();


                editTextBloodSugar.setText("");
                editTextBloodPressure.setText("");
                editTextWeight.setText("");
                editTextHeight.setText("");
                BMIComment.setText("");
                TextViewBMI.setText("");

                String BloodSugarkey = Bloodsugar.getText().toString();
                String BloodPressurekey = BloodPressure.getText().toString();
                String Weightkey = Weight.getText().toString();
                String Heightkey = Height.getText().toString();
                String BMIkey = BMI.getText().toString();

                String BloodSugarvalue = editTextBloodSugar.getText().toString();
                String BloodPressurevalue = editTextBloodPressure.getText().toString();
                String Weightvalue = editTextWeight.getText().toString();
                String Heightvalue = editTextHeight.getText().toString();
                String BMIvalue = TextViewBMI.getText().toString();

                String BMIStatus = BMIComment.getText().toString();

                String user_id = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


                currrent_user_db.child(BloodSugarkey).setValue(BloodSugarvalue);
                currrent_user_db.child(BloodPressurekey).setValue(BloodPressurevalue);
                currrent_user_db.child(Weightkey).setValue(Weightvalue);
                currrent_user_db.child(Heightkey).setValue(Heightvalue);
                currrent_user_db.child(BMIkey).setValue(BMIvalue);
                currrent_user_db.child("BMI_Status").setValue(BMIStatus);


            }
        });
        return v;

    }

    public void calculateBMI(){
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        if(heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)){
            float heightvalue = Float.parseFloat(heightStr) / 100;
            float weightvalue = Float.parseFloat(weightStr);

            float bmi = weightvalue / (heightvalue * heightvalue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }



        String bmivalue = bmi+"";

        TextViewBMI.setText(bmivalue);

        BMIComment.setText(bmiLabel);

        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference DBRef_Status = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        DBRef_Status.child("BMI_Status").setValue(bmiLabel);




        if (bmi < 18.5){



        } else if (bmi >= 25){


        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference dbgetsugar = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Blood Sugar");
        DatabaseReference dbgetpressure = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Blood Pressure");
        DatabaseReference dbgetweight = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Weight");
        DatabaseReference dbgetheight = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Height");
        DatabaseReference dbgetBMI = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("BMI");
        DatabaseReference dbgetBMIStatus = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("BMI_Status");



        dbgetsugar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {


                    String getSugarVal = String.valueOf(dataSnapshot.getValue());
                    editTextBloodSugar.setText(getSugarVal);
                }
                else
                {
                    editTextBloodSugar.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetpressure.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                String getpressureVal = String.valueOf(dataSnapshot.getValue());
                editTextBloodPressure.setText(getpressureVal);
            }
            else {
                editTextBloodPressure.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbgetweight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                String getweightVal = String.valueOf(dataSnapshot.getValue());
                editTextWeight.setText(getweightVal);

                }
                else {
                    editTextWeight.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetheight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                String getheightVal = String.valueOf(dataSnapshot.getValue());
                editTextHeight.setText(getheightVal);

                }
                else {
                    editTextHeight.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetBMI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                String getBMIval = String.valueOf(dataSnapshot.getValue());
                TextViewBMI.setText(getBMIval);

                }
                else {
                    TextViewBMI.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetBMIStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){

                String getBMIstatus = String.valueOf(dataSnapshot.getValue());
                BMIComment.setText(getBMIstatus);

            }
            else {
                BMIComment.setText("");
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




            }
}
