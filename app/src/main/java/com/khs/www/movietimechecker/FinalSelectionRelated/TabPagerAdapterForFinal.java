package com.khs.www.movietimechecker.FinalSelectionRelated;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapterForFinal extends FragmentStatePagerAdapter {


    //첫번째 탭과 두번째 탭
    FinalTitleSortFrag tabFragment1 = new FinalTitleSortFrag();
    FinalTheaterSortFrag tabFragment2 = new FinalTheaterSortFrag();
    private int tabCount;

    public TabPagerAdapterForFinal(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return tabFragment1;
            case 1:
                return tabFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}



