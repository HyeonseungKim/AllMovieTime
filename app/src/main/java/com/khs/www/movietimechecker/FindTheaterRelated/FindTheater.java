package com.khs.www.movietimechecker.FindTheaterRelated;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khs.www.movietimechecker.R;

import org.json.JSONArray;

import java.util.List;
import java.util.Locale;


public class FindTheater extends Fragment {

    FindTheaterAdapter mAdapter2;
    JSONArray mainLocationArr;
    ProgressDialog mDlg;
    LocationManager lm;
    TextView curPos;
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            double longitude = location.getLongitude();       //경도
            double latitude = location.getLatitude();         //위도

            String whereyouare = getAddress(latitude, longitude);
            curPos.setText(whereyouare);
            mDlg.dismiss();

        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    RecyclerView mMainLocList;
    RecyclerView.LayoutManager layoutManager;
    //국내 주요 지역
    String koreaAddr = "[{\"code\":\"0105001\",\"name\":\"서울\"}," +
            "{\"code\":\"0105002\",\"name\":\"경기\"}," +
            "{\"code\":\"0105003\",\"name\":\"강원\"}," +
            "{\"code\":\"0105004\",\"name\":\"충북\"}," +
            "{\"code\":\"0105005\",\"name\":\"충남\"}," +
            "{\"code\":\"0105006\",\"name\":\"경북\"}," +
            "{\"code\":\"0105007\",\"name\":\"경남\"}," +
            "{\"code\":\"0105008\",\"name\":\"전북\"}," +
            "{\"code\":\"0105009\",\"name\":\"전남\"}," +
            "{\"code\":\"0105010\",\"name\":\"제주\"}," +
            "{\"code\":\"0105011\",\"name\":\"부산\"}," +
            "{\"code\":\"0105012\",\"name\":\"대구\"}," +
            "{\"code\":\"0105013\",\"name\":\"대전\"}," +
            "{\"code\":\"0105014\",\"name\":\"울산\"}," +
            "{\"code\":\"0105015\",\"name\":\"인천\"}," +
            "{\"code\":\"0105016\",\"name\":\"광주\"}," +
            "{\"code\":\"0105017\",\"name\":\"세종\"}]";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.findtheater_layout, container, false);
        mMainLocList = view.findViewById(R.id.mainlocationlist);
        mMainLocList.setHasFixedSize(true);
        curPos = view.findViewById(R.id.curpos);
        try {
            mainLocationArr = new JSONArray(koreaAddr);
        } catch (Exception e) {

        }
        mAdapter2 = new FindTheaterAdapter(getActivity(), mainLocationArr);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        mMainLocList.setAdapter(mAdapter2);
        mMainLocList.setLayoutManager(layoutManager);


        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);//위치정보 객체를 생성

        curPos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//만약 위치정보 조회 권한이 앱에 없다면

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {//이유 설명이 사용자에게 필요한 경우
                        Snackbar.make(getActivity().findViewById(R.id.coordinatorlayout), "현위치 조회 권한이 필요합니다", 3000).setAction("권한 승인", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, TheaterSelectionTabBoard.MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                            }
                        }).show();

                    } else {
                        Snackbar.make(getActivity().findViewById(R.id.coordinatorlayout), "현위치 조회 권한이 필요합니다", 3000).setAction("권한 승인", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, TheaterSelectionTabBoard.MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                            }
                        }).show();
                    }
                } else {//이미 앱에 위치정보 조회 권한이 있다면 위치 요청
                    turnOnGPS();
                }
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        lm.removeUpdates(mLocationListener);//위치정보 객체에 이벤트 해제
    }


    public void turnOnGPS() {
        try {
            if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//gps가 켜진 경우
                mDlg = new ProgressDialog(getActivity());
                mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDlg.setMessage("현재 위치 조회 중");
                mDlg.show();

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, mLocationListener);
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1, mLocationListener);

            } else {
//gps 꺼진 경우
                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            }
        } catch (SecurityException e) {
            Toast.makeText(getActivity(), "현위치 조회 찾기에 실패했습니다.", Toast.LENGTH_SHORT);
        }
    }

    // 위도, 경도 -> 주소
    public String getAddress(double lat, double lng) {
        String address = null;

        //위치정보를 활용하기 위한 구글 API 객체
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        //주소 목록을 담기 위한 HashMap
        List<Address> list = null;

        try {
            list = geocoder.getFromLocation(lat, lng, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list == null) {
            Toast.makeText(getActivity(), "현위치 조회 찾기에 실패했습니다.", Toast.LENGTH_SHORT);
            return null;
        }

        if (list.size() > 0) {
            Address addr = list.get(0);
            address = addr.getAddressLine(0).toString();
        }
        return address;
    }
}
