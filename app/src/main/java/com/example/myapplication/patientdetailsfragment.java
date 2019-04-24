package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class patientdetailsfragment extends AppCompatActivity {

    public static final String PAT_Mob = "Mobile";

  TextView tvname;
  TextView tvage;
  TextView tvbloodsugar;
  TextView tvbp;
  TextView tvweight;
  TextView tvheight;
  TextView tvbmi;
  TextView tvbmistatus;
  TextView tvriskscore;
  Button btncallpatient;
  String mobile2;
  String nameval;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_frag_patientdetails);

        tvname = (TextView) findViewById(R.id.namedisplay);
        tvage = (TextView) findViewById(R.id.agedisplay);
        tvbloodsugar=(TextView) findViewById(R.id.bloodsugardisplay);
        tvbp = (TextView) findViewById(R.id.bpdisplay);
        tvweight = (TextView) findViewById(R.id.weightdisplay);
        tvheight = (TextView) findViewById(R.id.heightdisplay);
        tvbmi = (TextView) findViewById(R.id.bmidisplay);
        tvbmistatus = (TextView) findViewById(R.id.bmistatusdisplay);
        tvriskscore = (TextView) findViewById(R.id.riskscoredisplay);
        btncallpatient = (Button) findViewById(R.id.callpatient);

// toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.addnewtoolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();

        String mobile = intent.getStringExtra(DocPatientInfoDirFragment.PAT_Mob);



        DatabaseReference weightref = FirebaseDatabase.getInstance().getReference("Users").child(mobile);
        Log.e("this is weighttest",weightref.getKey());

        DatabaseReference databasePats2 = FirebaseDatabase.getInstance().getReference().child("Users");
        Log.e("Patient mob", mobile);


        weightref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.e("Weight: ","IN HERE");


                databasePats2.orderByChild("Mobile").equalTo(mobile).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                        if(String.valueOf(dataSnapshot.child("Name").getValue()).equals("null")) {
                            tvname.setText("");
                        }else {
                            nameval = String.valueOf(dataSnapshot.child("Name").getValue());
                            tvname.setText(nameval);
                            Log.e("name: ", nameval);
                        }

                        if(String.valueOf(dataSnapshot.child("Age").getValue()).equals("null")) {
                            tvage.setText("");
                        }else {
                            String ageval = String.valueOf(dataSnapshot.child("Age").getValue());
                            tvage.setText(ageval);
                            Log.e("age: ", ageval);

                        }
                        if(String.valueOf(dataSnapshot.child("Blood Sugar").getValue()).equals("null")) {
                            tvbloodsugar.setText("");
                        }else {
                            String bsval = String.valueOf(dataSnapshot.child("Blood Sugar").getValue());
                            tvbloodsugar.setText(bsval);
                            Log.e("Weight: ", "IN HERE");

                        }
                        if(String.valueOf(dataSnapshot.child("Blood Pressure").getValue()).equals("null")) {
                            tvbp.setText("");
                        }else {
                            String bpval = String.valueOf(dataSnapshot.child("Blood Pressure").getValue());
                            tvbp.setText(bpval);
                            Log.e("Weight: ", "IN HERE");

                        }

                        mobile2 = String.valueOf(dataSnapshot.child("Mobile").getValue());


                        if(String.valueOf(dataSnapshot.child("Weight").getValue()).equals("null")) {
                            tvweight.setText("");
                        }else{
                            String weightval = String.valueOf(dataSnapshot.child("Weight").getValue());
                            tvweight.setText(weightval);

                        }

                        if(String.valueOf(dataSnapshot.child("Height").getValue()).equals("null")) {
                            tvheight.setText("");
                            Log.e("if tvHeight",String.valueOf(dataSnapshot.child("Height").getValue()));
                        }else{
                            Log.e("else tvHeight",String.valueOf(dataSnapshot.child("Height").getValue()));
                            String heightval = String.valueOf(dataSnapshot.child("Height").getValue());
                            tvheight.setText(heightval);

                        }
                        if(String.valueOf(dataSnapshot.child("BMI").getValue()).equals("null")) {
                            tvbmi.setText("");
                            Log.e("ifbmi ",String.valueOf(dataSnapshot.child("BMI").getValue()));
                        }else {
                            String bmival = String.valueOf(dataSnapshot.child("BMI").getValue());
                            tvbmi.setText(bmival);

                        }
                        if(String.valueOf(dataSnapshot.child("BMI_Status").getValue()).equals("null")) {
                            tvbmistatus.setText("");
                        }else {
                            String bmistatusval = String.valueOf(dataSnapshot.child("BMI_Status").getValue());
                            tvbmistatus.setText("(" + bmistatusval + ")");

                        }
                        if(String.valueOf(dataSnapshot.child("Risk").getValue()).equals("null")) {
                            tvriskscore.setText("");
                        }else {
                            String riskscoreval = String.valueOf(dataSnapshot.child("Risk").getValue());
                            tvriskscore.setText(riskscoreval);

                        }

                        btncallpatient.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                callPhoneNumber(mobile2,nameval);

                            }
                        });





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
                                /*    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        String weightval = String.valueOf(dataSnapshot1.getValue());
                        Log.e("Weight: gg ",weightval);
                        tvweight.setText(weightval);
                    }*/

                /*Log.e("Patient Details","in here");

                    String uid = dataSnapshot.getRef().getKey();
                    Log.e("Patient uid",uid);*/


                    //String uid = dataSnapshot.ref;
                    //Log.e("Patient Details",uid);



                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void callPhoneNumber(String mobile, String name)
    {


        try
        {


            if(Build.VERSION.SDK_INT > 22)
            {


                if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }




                Toast.makeText(this.getApplicationContext(),"Calling Patient. " + name, Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobile));
                startActivity(callIntent);

            }
            else {
                Toast.makeText(this.getApplicationContext(),"Calling Patient.  " + name, Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobile));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber(mobile2,nameval);
            }
            else
            {
                String TAG = null;
                Log.e(TAG, "Permission not Granted");
            }
        }
    }
}

