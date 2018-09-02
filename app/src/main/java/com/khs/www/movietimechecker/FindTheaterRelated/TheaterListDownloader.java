package com.khs.www.movietimechecker.FindTheaterRelated;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.khs.www.movietimechecker.ETC.CustomLoadingIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//커스텀 쓰레드로 영화관 리스트 다운로드
public class TheaterListDownloader extends Thread {

    Fragment whocalledthis;
    String mainLocationCode;
    ArrayList<OneSpecificLoc> selectedArr;
    CustomLoadingIcon mcL = null;
    ArrayList<OneTheaterInfo> AL = new ArrayList<>();

    HttpURLConnection urlConnection = null;
    URL url;
    OutputStream os;


    public TheaterListDownloader(String mainLocationCode, ArrayList<OneSpecificLoc> selectedArr, Fragment frag) {
        super("theaterlistdownloader");
        this.whocalledthis = frag;
        this.mainLocationCode = mainLocationCode;
        this.selectedArr = selectedArr;
    }

    @Override
    public void run() {

        Handler HandlerforUi = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message m) {
                if (m.what == 0) {
                    mcL = new CustomLoadingIcon(whocalledthis.getActivity());
                    mcL.show();
                } else {
                    ((TheaterList) whocalledthis).showTheaterList(AL);
                    mcL.dismiss();
                }
            }
        };

        HandlerforUi.sendEmptyMessage(0);
//각 세부지역이 담긴 배열을 순회하며 각 세부지역 내 영화관 조회


        for (OneSpecificLoc oS : selectedArr) {
            downloadTheatersInEachSpecificLoc(oS);
        }
        HandlerforUi.sendEmptyMessage(1);
//
        super.run();
    }


    public void downloadTheatersInEachSpecificLoc(OneSpecificLoc oS) {


        try {
            String body = "sWideareaCd=" + mainLocationCode + "&sBasareaCd=" + oS.code;
            url = new URL("http://www.kobis.or.kr/kobis/business/mast/thea/findTheaCdList.do");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = urlConnection.getOutputStream();
            os.write(body.getBytes());
            os.flush();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

                String inputLine = "";
                String resultString = "";
                String cdValue;
                String cdNmValule;

                while ((inputLine = in.readLine()) != null) {
                    resultString = resultString + inputLine;
                }

                JSONObject jo = new JSONObject(resultString.trim());
                JSONArray fullarr = jo.getJSONArray("theaCdList");

                for (int i = 0; i < fullarr.length(); i++) {
                    jo = fullarr.getJSONObject(i);
                    cdValue = jo.getString("cd");
                    cdNmValule = jo.getString("cdNm");
                    AL.add(new OneTheaterInfo(cdValue, cdNmValule));
                }
                in.close();
            } else {
                throw new Exception("network error");
            }
        } catch (Exception e) {

        } finally {
            urlConnection.disconnect();
        }
    }
}
