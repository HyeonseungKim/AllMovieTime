package com.khs.www.movietimechecker.FindTheaterRelated;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard.mSnackBar;


public class TheaterList extends Fragment {


    ArrayList<OneSpecificLoc> selectedSpecificLocs;
    ArrayList<OneTheaterInfo> selectedTheaterArr;
    final View.OnClickListener checkComplete = new View.OnClickListener() {
        public void onClick(View v) {
            theaterSelected();
        }
    };
    String mainLocationCode;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    final View.OnClickListener allCheck = new View.OnClickListener() {//전체 선택 or 선택 해제
        public void onClick(View v) {
            int count = mAdapter.getItemCount();
            for (int i = 0; i < count; i++) {
                selectedTheaterArr.get(i).isChecked = true;
            }

            recyclerView.getAdapter().notifyDataSetChanged();
            theaterSelected();
        }
    };
    RecyclerView.LayoutManager layoutManager;
    CoordinatorLayout mCoordinator;
    FloatingActionButton fab;
    TheaterListDownloader tld;

    public void setArgs(ArrayList<OneSpecificLoc> selectedArr, String mainLocationCode) {

        selectedSpecificLocs = selectedArr;
        this.mainLocationCode = mainLocationCode;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tld = new TheaterListDownloader(mainLocationCode, selectedSpecificLocs, this);
        tld.start();

        View view = inflater.inflate(R.layout.theaterlist_layout, container, false);
        fab = getActivity().findViewById(R.id.fab);
        mCoordinator = getActivity().findViewById(R.id.coordinatorlayout);
        mSnackBar = Snackbar.make(mCoordinator, null, Snackbar.LENGTH_INDEFINITE);
        recyclerView = view.findViewById(R.id.theaterList);

        setupFAB();

        return view;
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

    public void theaterSelected() {

        ArrayList<OneTheaterInfo> selectedArray = new ArrayList<OneTheaterInfo>();

        for (OneTheaterInfo a : selectedTheaterArr) {
            if (a.isChecked == true) {
                selectedArray.add(a);
            }
        }
        if (selectedArray.size() != 0) {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("selTheater", selectedArray);
            getActivity().setResult(RESULT_OK, intent);
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "영화관을 선택해주세요", Toast.LENGTH_SHORT).show();
        }
    }


    public void showTheaterList(ArrayList<OneTheaterInfo> AL) {
        selectedTheaterArr = AL;

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new TheaterListAdapter(AL, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
//TheaterListDownloader 쓰레드 정리
        tld.interrupt();
        tld = null;
    }

}
