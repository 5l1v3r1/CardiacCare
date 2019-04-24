package com.example.myapplication;


import com.google.firebase.auth.FirebaseAuth;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Riskcalc_Fragment extends Fragment {




    public static final String TAG = "MyActivity";

    private FirebaseAuth firebaseAuth;

    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioGroup radioGroup4;
    RadioGroup radioGroup5;
    RadioGroup radioGroup6;
    RadioGroup radioGroup7;
    EditText age_;
    EditText pressure_;
    Button calculate;
    RadioButton gender_;
    RadioButton smoke_;
    RadioButton diabetes_;
    RadioButton attack_;
    RadioButton history_;
    RadioButton murmur_;
    RadioButton electro_;
    Button viewhistory;
    Toolbar myToolbar;
    int score;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_riskcalc, null);

        Toast.makeText(getContext(), "This Tool is For Patients Aged between 55 to 94 Only", Toast.LENGTH_LONG).show();

        //myToolbar = (Toolbar) v.findViewById(R.id.my_toolbar);

        //((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        //setHasOptionsMenu(true);
        firebaseAuth = FirebaseAuth.getInstance();



        radioGroup1 = (RadioGroup) v.findViewById(R.id.rg1);
        radioGroup2 = (RadioGroup) v.findViewById(R.id.rg2);
        radioGroup3 = (RadioGroup) v.findViewById(R.id.rg3);
        radioGroup4 = (RadioGroup) v.findViewById(R.id.rg4);
        radioGroup5 = (RadioGroup) v.findViewById(R.id.rg5);
        radioGroup6 = (RadioGroup) v.findViewById(R.id.rg6);
        radioGroup7 = (RadioGroup) v.findViewById(R.id.rg7);
        viewhistory = (Button) v.findViewById(R.id.btnviewHistory);
        age_ = (EditText) v.findViewById(R.id.add_age);
        pressure_ = (EditText) v.findViewById(R.id.add_pressure);
        calculate = (Button) v.findViewById(R.id.calc);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                // get selected radio button from radioGroup
                int selectedId1 = radioGroup1.getCheckedRadioButtonId();

                int selectedId2 = radioGroup2.getCheckedRadioButtonId();

                int selectedId3 = radioGroup3.getCheckedRadioButtonId();

                int selectedId4 = radioGroup4.getCheckedRadioButtonId();

                int selectedId5 = radioGroup5.getCheckedRadioButtonId();

                int selectedId6 = radioGroup6.getCheckedRadioButtonId();

                int selectedId7 = radioGroup7.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                gender_ =  v.findViewById(selectedId1);

                smoke_ = v.findViewById(selectedId2);

                diabetes_ = v.findViewById(selectedId3);

                history_ = v.findViewById(selectedId4);

                attack_ = v.findViewById(selectedId5);

                murmur_ = v.findViewById(selectedId6);

                electro_ = v.findViewById(selectedId7);


                String gender = gender_.getText().toString();

                String smoke = smoke_.getText().toString();

                String diabetes = diabetes_.getText().toString();

                String history = history_.getText().toString();

                String attack = attack_.getText().toString();

                String murmur = murmur_.getText().toString();

                String electro = electro_.getText().toString();

                String age = age_.getText().toString();

                String pressure = pressure_.getText().toString();




                score = 0;



                    if(!age.matches("")){
                        Log.e(TAG,"In age not null");
                        int age__ = Integer.parseInt(age);



                        if(!pressure.matches("")){
                            int pressure__ = Integer.parseInt(pressure);


                            Log.e(TAG,"In pressure not null");
                            if (age__ > 54 && age__ < 95) {
                                if (pressure__ > 89 && pressure__ < 201) {
                                    //age starts here---------->>>>>>>>>>>>>>>>>>>>>>>>>>>
                                    if (age__ > 54 && age__ < 60) {

                                        score = score + 0;
                                    } else if (age__ > 59 && age__ < 63) {

                                        score = score + 1;
                                    } else if (age__ > 62 && age__ < 67) {

                                        score = score + 2;
                                    } else if (age__ > 66 && age__ < 72) {

                                        score = score + 3;
                                    } else if (age__ > 71 && age__ < 75) {

                                        score = score + 4;
                                    } else if (age__ > 74 && age__ < 78) {

                                        score = score + 5;
                                    } else if (age__ > 77 && age__ < 82) {

                                        score = score + 6;
                                        Log.e(TAG, "AGE");
                                    } else if (age__ > 81 && age__ < 86) {

                                        score = score + 7;
                                    } else if (age__ > 85 && age__ < 91) {

                                        score = score + 8;
                                    } else if (age__ > 90 && age__ < 94) {

                                        score = score + 9;
                                    } else if (age__ > 93) {

                                        score = score + 10;
                                    }
                                    //age ends here------------------->>>>>>>>>>>>>>>>>>>>>>>>

                                    //gender start----------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                    if (gender.equals("Male")) {
                                        score = score + 0;
                                        Log.e(TAG, "Male");
                                    } else {
                                        score = score + 6;
                                        Log.e(TAG, "Female");
                                    }
                                    //gender end----------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                    //blood pressure start----------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                    if (pressure__ < 120) {
                                        score = score + 0;
                                    } else if (pressure__ > 119 && pressure__ < 140) {
                                        score = score + 1;
                                    } else if (pressure__ > 139 && pressure__ < 160) {
                                        score = score + 2;
                                    } else if (pressure__ > 159 && pressure__ < 180) {
                                        score = score + 3;
                                    } else if (pressure__ > 179) {

                                        score = score + 4;
                                    }
                                    //blood pressure end----------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                    //diabetes start------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                    if (diabetes.equals("Yes")) {
                                        score = score + 5;
                                    } else {
                                        score = score + 0;
                                    }
                                    //diabetes end------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                    //stroke start------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                    if (attack.equals("Yes")) {
                                        score = score + 6;
                                        Log.e(TAG, "ATTACK");
                                    } else {
                                        score = score + 0;
                                    }

                                    if (score == 0 || score == 1) {
                                        /*Toast.makeText(getContext(), "5% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "5";
                                        onclickcont(result);


                                    }

                                    if (score == 2 || score == 3) {
                                        /*Toast.makeText(getContext(), "6% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/

                                        String result = "6";
                                        onclickcont(result);

                                    }

                                    if (score == 4) {
                                        /*Toast.makeText(getContext(), "7% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "7";
                                        onclickcont(result);

                                    }

                                    if (score == 5) {
                                        /*Toast.makeText(getContext(), "8% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/

                                        String result = "8";
                                        onclickcont(result);

                                    }

                                    if (score == 6 || score == 7) {
                                        /*                            Toast.makeText(getContext(), "9% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/

                                        String result = "9";
                                        onclickcont(result);

                                    }

                                    if (score == 8) {
                                        /*Toast.makeText(getContext(), "11% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/

                                        String result = "11";
                                        onclickcont(result);

                                    }

                                    if (score == 9) {
                                        /*Toast.makeText(getContext(), "12% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "12";
                                        onclickcont(result);

                                    }

                                    if (score == 10) {
                                        /*Toast.makeText(getContext(), "13% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "13";
                                        onclickcont(result);

                                    }

                                    if (score == 11) {
                                        /*Toast.makeText(getContext(), "14% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "14";
                                        onclickcont(result);

                                    }

                                    if (score == 12) {
                                        /*Toast.makeText(getContext(), "16% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "16";
                                        onclickcont(result);

                                    }

                                    if (score == 13) {
                                        /*Toast.makeText(getContext(), "18% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "18";
                                        onclickcont(result);

                                    }

                                    if (score == 14) {
                                        /*Toast.makeText(getContext(), "19% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "19";
                                        onclickcont(result);

                                    }

                                    if (score == 15) {
                                        /*Toast.makeText(getContext(), "21% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "21";
                                        onclickcont(result);

                                    }

                                    if (score == 16) {
                                        /*Toast.makeText(getContext(), "24% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "24";
                                        onclickcont(result);

                                    }

                                    if (score == 17) {
                                        /*Toast.makeText(getContext(), "26% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "26";
                                        onclickcont(result);

                                    }

                                    if (score == 18) {
                                        /*Toast.makeText(getContext(), "28% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "28";
                                        onclickcont(result);

                                    }

                                    if (score == 19) {
                                        /*                            Toast.makeText(getContext(), "31% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "31";
                                        onclickcont(result);

                                    }

                                    if (score == 20) {
                                        /*                            Toast.makeText(getContext(), "34% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "34";
                                        onclickcont(result);

                                    }

                                    if (score == 21) {
                                        /*                            Toast.makeText(getContext(), "37% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "37";
                                        onclickcont(result);

                                    }

                                    if (score == 22) {
                                        /*                            Toast.makeText(getContext(), "41% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "41";
                                        onclickcont(result);

                                    }

                                    if (score == 23) {
                                        /*                            Toast.makeText(getContext(), "44% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "44";
                                        onclickcont(result);

                                    }

                                    if (score == 24) {
                                        /*                            Toast.makeText(getContext(), "48% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "48";
                                        onclickcont(result);

                                    }

                                    if (score == 25) {
                                        /*                            Toast.makeText(getContext(), "51% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "51";
                                        onclickcont(result);

                                    }

                                    if (score == 26) {
                                        /*                            Toast.makeText(getContext(), "55% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "55";
                                        onclickcont(result);

                                    }

                                    if (score == 27) {
                                        /*                            Toast.makeText(getContext(), "59% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "59";
                                        onclickcont(result);

                                    }

                                    if (score == 28) {
                                        /*                            Toast.makeText(getContext(), "63% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "63";
                                        onclickcont(result);

                                    }

                                    if (score == 29) {
                                        /*                            Toast.makeText(getContext(), "67% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "67";
                                        onclickcont(result);

                                    }

                                    if (score == 30) {
                                        /*                            Toast.makeText(getContext(), "71% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "71";
                                        onclickcont(result);

                                    }

                                    if (score == 31) {
                                        /*                            Toast.makeText(getContext(), "75% predicted risk of heart stroke or death in 5 years", Toast.LENGTH_SHORT).show();*/
                                        String result = "75";
                                        onclickcont(result);

                                    }
                                } else {
                                    Toast.makeText(getContext(), "The Blood Pressure should be between 90 & 200", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "The age should be between 55 & 94", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getContext(), "Pressure cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Age cannot be empty", Toast.LENGTH_SHORT).show();
                    }




               /* public void onAlertclick(){
                    //firebase save
                }
*/
               /* new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) smok,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) diabete,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) histor,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) attac,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) murmu,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getContext(), (CharSequence) electr,
                                Toast.LENGTH_SHORT).show();
                    }
                }, 2000);*/


            }
        });

        viewhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), listView_Riskcalc.class);
                startActivity(intent);
            }
        });



        Log.e(TAG,"In v");
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        Log.e(TAG,"In menuinfla");


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();





        return true;
    }





    public void onclickcont(final String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final String Date = getcalander();

        final DatabaseReference reference;
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Barplot");*/

        String user_id = firebaseAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("BarPlot");

        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        dbreference.child("Risk").setValue(result+"%");


        builder.setMessage("this is message");
        builder.setTitle(result+ "% predicted risk of heart stroke or death in 5 years");

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to save this result?");
        //This will not allow to close dialogbox until user selects an option
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String ids = reference.push().getKey();
                reference.child(ids).child("xValue").setValue(Date);
                reference.child(ids).child("yValue").setValue(result);

                Toast.makeText(getContext(), "Saved Result", Toast.LENGTH_SHORT).show();
                //builder.finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                Toast.makeText(getContext(), "Result not saved", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
        alert.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    public String getcalander(){
        Calendar calander;
        SimpleDateFormat simpledateformat;
        String Date;
        String DisplayDateTime = null;
        Button BTN;

            calander = Calendar.getInstance();
            simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date = simpledateformat.format(calander.getTime());
            return Date;
}


}

//fetch date and push date and result in firebase
//fetch from firebase and display graph