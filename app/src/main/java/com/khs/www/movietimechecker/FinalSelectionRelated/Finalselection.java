package com.khs.www.movietimechecker.FinalSelectionRelated;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.khs.www.movietimechecker.FindTheaterRelated.OneTheaterInfo;
import com.khs.www.movietimechecker.HotMovieRelated.HotMovieItem;
import com.khs.www.movietimechecker.R;

import java.util.ArrayList;


public class Finalselection extends AppCompatActivity {

    public ViewPager viewPager;
    Toolbar toolbar;
    ArrayList<HotMovieItem> selectedMovieTitleArrForFinalSelection; //선택된 영화 제목들
    ArrayList<OneTheaterInfo> selectedTheaterArrForFinalSelection; //선택된 영화관 정보들
    String date;//관람 날짜
    String time;//관람 시간
    String dateAndTime;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalselection_layout);

        Intent intentFromMainBoard = getIntent();
        selectedMovieTitleArrForFinalSelection = intentFromMainBoard.getParcelableArrayListExtra("selMovieTitleArr");
        selectedTheaterArrForFinalSelection = intentFromMainBoard.getParcelableArrayListExtra("selTheaterArr");
        date = intentFromMainBoard.getStringExtra("selDate");
        time = intentFromMainBoard.getStringExtra("selTime");
        dateAndTime = intentFromMainBoard.getStringExtra("selDateAndTime");


        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(dateAndTime);

        Toast.makeText(this, "영화관 이름을 클릭해보세요~", Toast.LENGTH_SHORT).show();

        FinalListDownloader fLD = new FinalListDownloader(selectedMovieTitleArrForFinalSelection, selectedTheaterArrForFinalSelection, date, time, this);
        fLD.execute();


        TabPagerAdapterForFinal pagerAdapter = new TabPagerAdapterForFinal(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}





