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


public class Frag6 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag6_layout, container, false);


        ArrayList<DietLayout> exampleList = new ArrayList<>();

        exampleList.add(new DietLayout("Breakfast",
                "1. One glass milk(Add 2 tsp skimmed milk powder)\n"+
                        "2. Two Vegetable stuffed paratha, 1 cup curd\n"+
                        "3. 6 or 7 Soaked Almonds"));

        exampleList.add(new DietLayout("Lunch",
                "1. One cup Sprout salad\n"+
                        "2. Two(medium with ghee) Chapati\n"+
                        "3. Two cups Veg(potato/ cauliflower/ cabbage/\n"+
                        "    ladyfinger/ bottlegourd/ ridgegourd/ brinjal\n"+
                        "    etc)\n"+
                        "4. One cup Dal / legumes(soyabean/ moth/ mung etc)\n"+
                        "5. One cup Rice / biryani / pulav(veg/ non veg)"));

        exampleList.add(new DietLayout("Snack",
                "1. One cup Tea / Coffee\n"+
                        "2. 4 Cookies\n"+
                        "3. 3 or 4 Groundnut chikki / Dry Fruit chikki"));

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
