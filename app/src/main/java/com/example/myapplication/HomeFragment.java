package com.example.myapplication;


import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntt.customgaugeview.library.GaugeView;



import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseReference dbref;

    TextView id_BMI;
    TextView id_BMIStatus;

    ////////////////////////////////////////////////////////////////////////////
    BarChart barChart;
    ////////////////////////////////////////////////////////////////////////////
    public static final String TAG = "list";
    ArrayList<String> myArrayList = new ArrayList<>();


    private FirebaseAuth firebaseAuth;

    DatabaseReference reference1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);

        id_BMI = (TextView) v.findViewById(R.id.id_BMI);
        id_BMIStatus = (TextView) v.findViewById(R.id.id_BMIStatus);

        firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference dbgetBMI = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("BMI");

        DatabaseReference dbgetBMIStatus = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("BMI_Status");


        final GaugeView gv_meter = (GaugeView) v.findViewById(R.id.gv_meter);
        gv_meter.setShowRangeValues(true);
        gv_meter.setTargetValue(0);

        dbgetBMI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Log.e(TAG,"main if ");


                    final String getBMIval = String.valueOf(dataSnapshot.getValue());
                    if(getBMIval.equals(""))
                    {
                        Log.e(TAG,"second if");
                        id_BMI.setText("Click Here To Calculate BMI");
                        id_BMI.setOnClickListener(new View.OnClickListener() {

                            @Override

                            public void onClick(View v) {
                                getFragmentManager().beginTransaction().replace(R.id.screen_area, new VitalsFragment() ).addToBackStack("").commit();
                            }
                        });
                    }
                    else {
                        Log.e(TAG,"else blank");

                        id_BMI.setText(getBMIval);
                        meterrun(getBMIval, gv_meter);
                    }
                } else {

                    Log.e(TAG,"Main else");


                    id_BMI.setText("Calculate Your BMI First");
                    id_BMI.setOnClickListener(new View.OnClickListener() {

                        @Override

                        public void onClick(View v) {
                            getFragmentManager().beginTransaction().replace(R.id.screen_area, new VitalsFragment() ).addToBackStack("").commit();
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetBMIStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    final String getBMIStatusval = String.valueOf(dataSnapshot.getValue());
                    if(getBMIStatusval.equals("")) {

                        id_BMIStatus.setText("");
                    }
                    else{
                        id_BMIStatus.setText("(" + getBMIStatusval + ")");
                    }
                }
                else {
                    id_BMIStatus.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        barChart = (BarChart) v.findViewById(R.id.bargraphhome);
        final ArrayList<BarEntry> barEntries = new ArrayList<>();
        final ArrayList<String> theDates = new ArrayList<>();
        //////////////////////////////////////////////////////////////////////////



        DatabaseReference reference1;
        reference1 = database.getReference().child("Users").child(uid).child("BarPlot");

        reference1.addChildEventListener(new ChildEventListener() {
            int z= 0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




/////////////////////////////////////////////////////////////////////
                Log.e(TAG,"result"+z);
                String date1 = dataSnapshot.child("xValue").getValue(String.class);

                String percentage3 = dataSnapshot.child("yValue").getValue(String.class);

               // Log.e(TAG,"PERCENTAGE "+Float.parseFloat(percentage1));
                try{

                barEntries.add(new BarEntry(Float.parseFloat(percentage3), z));

                }
                catch (NullPointerException npexception){
                }

                theDates.add(date1);
                //Log.e(TAG,"Date "+date1);


/////////////////////////////////////////////////////////////////////

                z++;

                barplot(barEntries,theDates);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









    /* final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms


            }

       }, 2000);*/






    return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    /*    id_BMI = (TextView) getActivity().findViewById(R.id.id_BMI);
        id_BMIStatus = (TextView) getActivity().findViewById(R.id.id_BMIStatus);

        firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference dbgetBMI = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("BMI");

        DatabaseReference dbgetBMIStatus = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("BMI_Status");


        final GaugeView gv_meter = (GaugeView) getActivity().findViewById(R.id.gv_meter);
        gv_meter.setShowRangeValues(true);
        gv_meter.setTargetValue(0);

        dbgetBMI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {


                    final String getBMIval = String.valueOf(dataSnapshot.getValue());
                    if(getBMIval=="")
                    {
                        id_BMI.setText("Calculate Your BMI First");
                        id_BMI.setOnClickListener(new View.OnClickListener() {

                            @Override

                            public void onClick(View v) {
                                getFragmentManager().beginTransaction().replace(R.id.screen_area, new VitalsFragment() ).addToBackStack("").commit();
                            }
                        });
                    }
                    else {
                        id_BMI.setText(getBMIval);
                        meterrun(getBMIval, gv_meter);
                    }
                } else {

                    id_BMI.setText("Calculate Your BMI First");
                    id_BMI.setOnClickListener(new View.OnClickListener() {

                        @Override

                        public void onClick(View v) {
                            getFragmentManager().beginTransaction().replace(R.id.screen_area, new VitalsFragment() ).addToBackStack("").commit();
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetBMIStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    final String getBMIStatusval = String.valueOf(dataSnapshot.getValue());
                    id_BMIStatus.setText("(" + getBMIStatusval + ")");
                }
                else {
                    id_BMIStatus.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        barChart = (BarChart) getActivity().findViewById(R.id.bargraphhome);
        final ArrayList<BarEntry> barEntries = new ArrayList<>();
        final ArrayList<String> theDates = new ArrayList<>();
        //////////////////////////////////////////////////////////////////////////



        DatabaseReference reference1;
        reference1 = database.getReference().child("Users").child(uid).child("BarPlot");

        reference1.addChildEventListener(new ChildEventListener() {
            int z= 0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




/////////////////////////////////////////////////////////////////////
                Log.e(TAG,"result"+z);
                String date1 = dataSnapshot.child("xValue").getValue(String.class);

                String percentage3 = dataSnapshot.child("yValue").getValue(String.class);

                // Log.e(TAG,"PERCENTAGE "+Float.parseFloat(percentage1));
                try{

                    barEntries.add(new BarEntry(Float.parseFloat(percentage3), z));

                }
                catch (NullPointerException npexception){
                }

                theDates.add(date1);
                //Log.e(TAG,"Date "+date1);


/////////////////////////////////////////////////////////////////////

                z++;

                barplot(barEntries,theDates);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }

               //////////////////////////////////////////////////////////////////////////



/*float s = Float.parseFloat("75");

        barEntries.add(new BarEntry(null,0));
        barEntries.add(new BarEntry(null,1));
        barEntries.add(new BarEntry(null,2));
        barEntries.add(new BarEntry(null,3));
        barEntries.add(new BarEntry(null,4));
        barEntries.add(new BarEntry(null,5));
        theDates.add("jan");
        theDates.add("feb");
        theDates.add("mar");
        theDates.add("apr");
        theDates.add("may");
        theDates.add("jun");*/

/*        ArrayList<BarEntry> barEntries1  = new ArrayList<>();
        ArrayList<String> theDates1 = new ArrayList<>();
        barEntries1 = barEntries;
        theDates1 = theDates;
        BarDataSet barDataSet = new BarDataSet(barEntries1,"Dates");
        BarData thedata = new BarData(theDates1,barDataSet);
        barChart.setData(thedata);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);*/


    public void meterrun(String s,GaugeView gv_meter) {

        CountDownTimer timer = new CountDownTimer(10000, 2) {




            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    gv_meter.setTargetValue(Float.valueOf(s));
                } catch (NumberFormatException e) {
                }
            }


            @Override
            public void onFinish() {

                try {
                    gv_meter.setTargetValue(Float.valueOf(s));
                }
                catch (NumberFormatException e){

                }
            }
        };
        timer.start();

    }
    private void barplot(ArrayList<BarEntry> barEntries, ArrayList<String> theDates){
        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
        BarData thedata = new BarData(theDates,barDataSet);
        barChart.setData(thedata);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setScaleXEnabled(true);
        barChart.setScaleYEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(true);
        barChart.setDescription("Risk History");
        barChart.animateY(3000);
        barChart.invalidate();
    }
}