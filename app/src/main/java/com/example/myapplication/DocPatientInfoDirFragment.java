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
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DocPatientInfoDirFragment extends Fragment {
    public static final String PAT_Mob = "Mobile";




    String mobile;
    String name;

    ListView patientinfolist;
    FirebaseAuth firebaseAuth;

    DatabaseReference databasePats;
    DatabaseReference databaseFilterPats;
    DatabaseReference dbmobilefetch1;

    List<PatsDir> patsdir;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_patientinfodir,null);

        databasePats = FirebaseDatabase.getInstance().getReference("Users");
        String u_id = FirebaseAuth.getInstance().getUid();
        databaseFilterPats = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Type");
        dbmobilefetch1 = FirebaseDatabase.getInstance().getReference().child("Users").child("Mobile");
        patientinfolist = (ListView) v.findViewById(R.id.idPatientDirList);


        patsdir = new ArrayList<>();



        patientinfolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final PatsDir PatsDirList = patsdir.get(position);

                name = PatsDirList.getName();
                mobile = PatsDirList.getMobile();

                Intent intent = new Intent(getActivity().getApplicationContext(), patientdetailsfragment.class);

                intent.putExtra(PAT_Mob, PatsDirList.getMobile());

                startActivity(intent);


            }
        });


        databasePats.orderByChild("Type").equalTo("Patient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PatsDir pat1 = postSnapshot.getValue(PatsDir.class);
                    patsdir.add(pat1);

                }



                if (getActivity() != null) {

                    PatsDirList PatAdapter1 = new PatsDirList(getActivity(), patsdir);
                    patientinfolist.setAdapter(PatAdapter1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        return v;

    }



    /*public void callPhoneNumber()
    {


        try
        {


            if(Build.VERSION.SDK_INT > 22)
            {


                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }




                Toast.makeText(getActivity().getApplicationContext(),"Calling Dr. " + name, Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobile));
                startActivity(callIntent);

            }
            else {
                Toast.makeText(getActivity().getApplicationContext(),"Calling Dr.  " + name, Toast.LENGTH_SHORT).show();
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
                callPhoneNumber();
            }
            else
            {
                String TAG = null;
                Log.e(TAG, "Permission not Granted");
            }
        }
    }
*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
