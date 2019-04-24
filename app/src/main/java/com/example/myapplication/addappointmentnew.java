package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class addappointmentnew extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    public static final String DOC_Name = "docname";


    FirebaseAuth firebaseAuth;
    TextView timesetter;
    Button btndateset;
    Button btntimeset;
    TextView aptdateset;
    TextView tvdocnamefetch;
    Button btnconfirmapt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.addptnnewtoolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        aptdateset = (TextView) findViewById(R.id.aptdateset);
        tvdocnamefetch = (TextView) findViewById(R.id.tvDocNameFetch);
        btnconfirmapt = (Button) findViewById(R.id.saveapt);
        btntimeset = (Button) findViewById(R.id.btntimeset);
        btndateset = (Button) findViewById(R.id.btndateset);
        timesetter = (TextView) findViewById(R.id.timesetter);
        Intent intent = getIntent();

        firebaseAuth = firebaseAuth.getInstance();

        btndateset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });



       /* calviewdoc.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                aptdateset.setText(date);

            }
        });
        */

        String aptdocname = intent.getStringExtra(NewAppointmentFragment.DOC_Name);

        tvdocnamefetch.setText(aptdocname);

        btntimeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        btnconfirmapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(aptdateset.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();



            }
               else if(timesetter.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Time", Toast.LENGTH_SHORT).show();

                }


              else {

                  String apptdate = aptdateset.getText().toString();

                  String docname = tvdocnamefetch.getText().toString();

                  String apttime = timesetter.getText().toString();

                  Log.e("No Date", apptdate);

                  String user_id = firebaseAuth.getCurrentUser().getUid();
                  DatabaseReference aptdb = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


                  Log.e("AllOK", apptdate + apttime);


                  aptdb.child("AppointmentDoctorName").setValue(docname);
                  aptdb.child("AppointmentDate").setValue(apptdate);
                  aptdb.child("AppointmentTime").setValue(apttime);

                  Toast.makeText(getApplicationContext(), "Appointment Booked Successfully", Toast.LENGTH_SHORT).show();

                  finish();
              }
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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timesetter = (TextView) findViewById(R.id.timesetter);
        timesetter.setText(String.format("%02d:%02d",hourOfDay, minute));
    }


    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        aptdateset.setText(currentDateString);
    }
}
