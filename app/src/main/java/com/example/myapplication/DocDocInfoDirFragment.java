package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DocDocInfoDirFragment extends Fragment {


    String mobile;
    String name;



    ListView doctorinfolist;
    FirebaseAuth firebaseAuth;

    List<DocsDir> docsdir;

    DatabaseReference databaseDocs;
    DatabaseReference databaseFilterDocs;
    DatabaseReference dbmobilefetch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_doctorinfodir,null);



        databaseDocs = FirebaseDatabase.getInstance().getReference("Users");
        String u_id = FirebaseAuth.getInstance().getUid();
        databaseFilterDocs = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Type");
        dbmobilefetch = FirebaseDatabase.getInstance().getReference().child("Users").child("Mobile");


        doctorinfolist = (ListView) v.findViewById(R.id.doctorinfolist);


        docsdir = new ArrayList<>();



        doctorinfolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DocsDir DocsDirList = docsdir.get(position);

                name = DocsDirList.getName();
                mobile = DocsDirList.getMobile();

                callPhoneNumber();

            }
        });



        databaseDocs.orderByChild("Type").equalTo("Doctor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DocsDir doc1 = postSnapshot.getValue(DocsDir.class);
                    docsdir.add(doc1);

                }


                if (getActivity() != null) {

                    DocsDirList DocAdapter1 = new DocsDirList(getActivity(), docsdir);
                    doctorinfolist.setAdapter(DocAdapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });





        return v;

    }

    public void callPhoneNumber()
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}
