package com.khs.www.movietimechecker.FinalSelectionRelated;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khs.www.movietimechecker.R;

import java.util.ArrayList;


public class FinalTheaterSortFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.finaltheatersortfrag_layout, container, false);
        recyclerView = view.findViewById(R.id.theatercenteredrecyclerview);
        return view;
    }


    public void showFinalTheaterCenteredList(ArrayList<OneFinalItem> AL) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new FinalTheaterSortFragAdapter(AL, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}
