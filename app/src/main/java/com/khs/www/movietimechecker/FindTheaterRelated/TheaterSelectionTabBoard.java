package com.khs.www.movietimechecker.FindTheaterRelated;

import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.khs.www.movietimechecker.ETC.Util;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.databinding.TheaterselectiontabboardLayoutBinding;

import java.util.List;
import java.util.Map;

import static com.khs.www.movietimechecker.FindTheaterRelated.FavoriteTheater.mPref;


public class TheaterSelectionTabBoard extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    static public Snackbar mSnackBar;
    TheaterselectiontabboardLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.theaterselectiontabboard_layout);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("영화관 선택");

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.pager.setAdapter(pagerAdapter);
        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
//화면 전환(영화관 찾기 -> 즐겨찾기 영화관) 시에 FAB 보여주고 스낵바 다시 세팅
                    binding.fab.setVisibility(View.VISIBLE);
                    ((TabPagerAdapter) binding.pager.getAdapter()).tabFragment1.setupFAB();
                    mSnackBar.dismiss();

                } else {
//화면 전환(즐겨찾기 영화관 -> 영화관 찾기) 시에 FAB 사라짐
//영화관 찾기 탭에서 프래그먼트 교체 시마다 addToBackStack(null)을 하지 않음으로써 뒤로 가기를 누르면 바로 메인화면으로 돌아감
//화면 전환 시에 FAB 클릭 시 각 프래그먼트에 맞도록 매번 스낵바 세팅
                    String whichFragmentinThree = getSupportFragmentManager().findFragmentById(R.id.rootfragment).getClass().getSimpleName();
                    if (whichFragmentinThree.equals("FindTheater")) {
                        binding.fab.setVisibility(View.GONE);
                    } else if (whichFragmentinThree.equals("SpecificLoc")) {
                        ((SpecificLoc) getSupportFragmentManager().findFragmentById(R.id.rootfragment)).setupFAB();
                    } else if (whichFragmentinThree.equals("TheaterList")) {
                        ((TheaterList) getSupportFragmentManager().findFragmentById(R.id.rootfragment)).setupFAB();
                    }
                    mSnackBar.dismiss();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//즐겨찾기한 영화관이 없는 경우 표시
        /*If a preferences file by this name
         * does not exist, it will be created when you retrieve an
         * editor (SharedPreferences.edit()) and then commit changes (Editor.commit())*/
        mPref = getSharedPreferences("favorites", Context.MODE_PRIVATE);
        mPref.edit().commit();
        Map<String, ?> allEntries = mPref.getAll();
        if (allEntries.size() == 0) {
            Util.makeCoachMark(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    List<Fragment> twoFragments = getSupportFragmentManager().getFragments();
                    for (Fragment mFrag : twoFragments) {
                        if (mFrag instanceof FindTheater) {
                            ((FindTheater) mFrag).turnOnGPS();
                        }
                    }
                } else {
                    Toast.makeText(this, "현위치 조회 권한을 거부하셨습니다", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }
}




