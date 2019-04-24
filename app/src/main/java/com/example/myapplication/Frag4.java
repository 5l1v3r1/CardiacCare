package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Frag4 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag4_layout, container, false);


        ArrayList<DietLayout> exampleList = new ArrayList<>();

        exampleList.add(new DietLayout("Breakfast",
                "1.Fresh Mosambi Juice\n"+
        "2.4 Steamed Idlis\n"+
        "3.Fresh Fruit(Apple/Chikoo/pear)\n"+
        "4.6 or 7 Dry Fruits(Roasted Almond/Pistachios)"));

        exampleList.add(new DietLayout("Lunch",
                "1. One cup Sprout salad\n"+
                        "2. Two(medium with ghee) Chapati\n"+
                        "3. Two cups Veg(potato/ cauliflower/ cabbage/\n"+
                        "    ladyfinger/ bottlegourd/ ridgegourd/ brinjal\n"+
                        "    etc)\n"+
                        "4. One cup Dal/Sambhar(soyabean/ moth/ mung etc)\n"+
                        "5. One cup Rice/biryani/pulav(veg/non veg)"));

        exampleList.add(new DietLayout("Snack",
                "1. One cup Tea / Coffee / Green Tea\n"+
                        "2. 4 Cookies/Biscuits\n"+
                        "3. Puffed Rice Chiwda"));

        exampleList.add(new DietLayout("Dinner",
                "1. Two (medium with ghee) Chapati\n"+
                        "2. One cup Rice / biryani / pulav (veg / non veg)\n"+
                        "3. Two cups Veg(potato/ cauliflower/ cabbage/\n" +
                        "    ladyfinger/ bottlegourd/ ridgegourd/ brinjal\n" +
                        "    etc)\n"+
                        "4. 6 or 7 Soaked Almonds \n"+
                        "5. One glass milk (Add two tsp skimmed milk powder)"));




        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }
}
