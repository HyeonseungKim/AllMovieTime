package com.khs.www.movietimechecker.FindTheaterRelated;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import com.khs.www.movietimechecker.databinding.FavoritetheaterLayoutBinding;

import java.util.ArrayList;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard.mSnackBar;


public class FavoriteTheater extends Fragment {

    static SharedPreferences mPref;
    FavoritetheaterLayoutBinding binding;
    FavoriteTheaterAdapter mAdapter;
    ArrayList<OneTheaterInfo> favoriteTheaterArr;
    final View.OnClickListener allCheck = new View.OnClickListener() {
        public void onClick(View v) {
            int count = mAdapter.getItemCount();

            for (int i = 0; i < count; i++) {
                favoriteTheaterArr.get(i).isChecked = true;
            }

            binding.favoritetheaterlist.getAdapter().notifyDataSetChanged();
            theaterSelected();
        }
    };
    final View.OnClickListener checkComplete = new View.OnClickListener() {
        public void onClick(View v) {
            theaterSelected();
        }
    };
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mPref = getActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE);
        mPref.edit().commit();
        Map<String, ?> allEntries = mPref.getAll();
        //즐겨찾기한 영화관 배열
        favoriteTheaterArr = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String code = entry.getKey();
            String name = entry.getValue().toString();
            OneTheaterInfo a = new OneTheaterInfo(code, name);
            favoriteTheaterArr.add(a);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.favoritetheater_layout, container, false);
        binding.setFragment(this);
        fab = getActivity().findViewById(R.id.fab);

        mAdapter = new FavoriteTheaterAdapter(getActivity(), favoriteTheaterArr);
        binding.favoritetheaterlist.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.favoritetheaterlist.setLayoutManager(layoutManager);
        // 스낵바 생성 및 FAB에 클릭 리스너 달기
        mSnackBar = Snackbar.make(getActivity().findViewById(R.id.coordinatorlayout), "", Snackbar.LENGTH_LONG);
        setupFAB();

        return binding.getRoot();
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
        ArrayList<OneTheaterInfo> selectedArray = new ArrayList<>();
        for (OneTheaterInfo a : favoriteTheaterArr) {
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
}
