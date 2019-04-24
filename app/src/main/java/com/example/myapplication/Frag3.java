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


public class Frag3 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag3_layout, container, false);


        ArrayList<DietLayout> exampleList = new ArrayList<>();

        exampleList.add(new DietLayout("Breakfast",
                "1.Apple/Carrot Juice\n"+
                        "2.Vegetable Stuffed Paratha(Light)\n"+
                        "3. 6 or 7 Soaked Almonds"));

        exampleList.add(new DietLayout("Lunch",
                "1. One cup Sprout salad\n"+
                        "2. Two(medium with ghee) Chapati/Fulkaas\n"+
                        "3. Two Cups Veg(seasonal green leafy vegetables)\n"+
                        "4. One cup Dal / legumes(soyabean/ moth/ mung etc)\n"+
                        "5. One cup Rice / biryani / pulav(veg/ non veg)\n"+
                        "6. One Papad"));

        exampleList.add(new DietLayout("Snack",
                "1. One cup Tea/Coffee/Green Tea\n"+
                        "2. 4 Cookies/Biscuits\n"+
                        "3. Diet Bhel"));

        exampleList.add(new DietLayout("Dinner",
                "1.Two(meduim with ghee) chapati/Fulka\n"+
        "2.One Cup Rice/Biryani/Pulav/ (veg/non veg)\n"+
        "3.Two Cups Veg(potato/bitter guard/cabbage/brinjal\n"+
                "/lady Finger/Pumpkin)\n"+
        "4.One Glass Milk"));




        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }
}
