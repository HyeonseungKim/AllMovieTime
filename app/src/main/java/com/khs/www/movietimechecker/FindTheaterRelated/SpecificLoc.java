package com.khs.www.movietimechecker.FindTheaterRelated;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.R;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard.mSnackBar;


public class SpecificLoc extends Fragment {

    JSONObject mJo ;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    CoordinatorLayout mCoordinator;
    FloatingActionButton fab;

    String mainLocationCode;

    ArrayList<OneSpecificLoc> specificLocArr;

    public SpecificLoc() {

    }

    public void setArgs(JSONObject mJO){
        try{
        this.mJo = mJO;
        mainLocationCode=mJo.getString("code");
    }catch(Exception e){

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specificloc_layout, container, false);
        fab = getActivity().findViewById(R.id.fab);
        mCoordinator = getActivity().findViewById(R.id.coordinatorlayout);
        mSnackBar = Snackbar.make(mCoordinator, null, Snackbar.LENGTH_INDEFINITE);
        recyclerView = view.findViewById(R.id.specificLocList);


        try {
            SpecificLocListDownloader sLLD = new SpecificLocListDownloader(getActivity(), SpecificLoc.this);
            sLLD.execute(mJo.getString("code"));
        }catch(Exception e){

        }

        setupFAB();

        return view;
    }

//FindTheater 프래그먼트에서 사라졌던 FAB를 다시 등장시킴
    @Override
    public void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }

    public void setupFAB() {
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSnackBar = Util.getCustomizedSnackView(getActivity(), mSnackBar, allCheck, checkComplete);
                mSnackBar.show();
            }
        });
    }


    final View.OnClickListener checkComplete = new View.OnClickListener() {
        public void onClick(View v) {
            specificLocSelected();
        }
    };


    final View.OnClickListener allCheck = new View.OnClickListener() {//전체 선택
        public void onClick(View v) {
            int count = mAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    specificLocArr.get(i).isChecked=true;
                }
            recyclerView.getAdapter().notifyDataSetChanged();
            specificLocSelected();
        }
    };


    public void showSpecificLoc(ArrayList<OneSpecificLoc> AL){

        specificLocArr = AL;

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new SpecificLocAdapter(AL, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }



    public void specificLocSelected() {

        ArrayList<OneSpecificLoc> selectedArray = new ArrayList<OneSpecificLoc>();

        for (OneSpecificLoc a : specificLocArr) {
            if (a.isChecked == true) {
                selectedArray.add(a);
            }
        }
        if (selectedArray.size() != 0) {
            TheaterList tL = new TheaterList();
            tL.setArgs(selectedArray, mainLocationCode);
            FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.rootfragment, tL, "thisistheaterlist");
            trans.commit();

            mSnackBar.dismiss();
        } else {
            Toast.makeText(getActivity(), "세부 지역을 선택해주세요", Toast.LENGTH_SHORT).show();
        }
    }
}
