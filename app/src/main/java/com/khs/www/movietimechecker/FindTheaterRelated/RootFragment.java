package com.khs.www.movietimechecker.FindTheaterRelated;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khs.www.movietimechecker.R;

// 뷰페이저 내의 프래그먼트 교체를 위한 방법, 비어있는 root fragment 내의 자식 프래그먼트에 실제 뷰들을 담는다.
public class RootFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rootfragment_layout, container, false);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.rootfragment, new FindTheater(), "findTheater");
        transaction.commit();
        return view;
    }

}
