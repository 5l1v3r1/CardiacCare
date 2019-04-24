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


public class Frag2 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag2_layout, container, false);


        ArrayList<DietLayout> exampleList = new ArrayList<>();

        exampleList.add(new DietLayout("Breakfast",
                "1. Orange Juice\n"+
                        "2.poha/upma\n"+
                        "3. 6 or 7 Soaked Almonds"));

        exampleList.add(new DietLayout("Lunch",
                "1. One cup Sprout salad\n"+
                        "2.Two (medium with ghee)Chapati/Fulkaas\n"+
                        "3. 3.Two Cups Veg(seasonal green leafy vegetables))\n"+
                        "4.One Cup Dal/legumes (mung/moth/Turr/etc)\n"+
                        "5. One cup Rice / biryani / pulav(veg/ non veg)"));

        exampleList.add(new DietLayout("Snack",
                "1. One cup Tea / Coffee\n"+
                        "2. 4 Cookies/Biscuits\n"+
                        "3. Puffed Rice Chiwda"));

        exampleList.add(new DietLayout("Dinner",
                "1. Two(meduim with ghee) chapati/Fulka\n"+
                        "2. One cup Rice / biryani / pulav (veg / non veg)\n"+
                        "3. Two cups Veg(potato/ cauliflower/ cabbage/\n" +
                        "    ladyfinger/ bottlegourd/ ridgegourd/ brinjal\n" +
                        "    etc)\n"+
                        "4. 6 or 7 Soaked Almonds \n"+
                        "5. One glass milk"));




        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }
}
