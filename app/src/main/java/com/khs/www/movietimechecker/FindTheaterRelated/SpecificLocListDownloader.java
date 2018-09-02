package com.khs.www.movietimechecker.FindTheaterRelated;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.khs.www.movietimechecker.ETC.CustomLoadingIcon;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class SpecificLocListDownloader extends AsyncTask<String, Void, ArrayList<OneSpecificLoc>> {

    Context mContext;
    Fragment whocalledthis;
    CustomLoadingIcon mcL = null;
    ArrayList<OneSpecificLoc> AL = new ArrayList<>();


    public SpecificLocListDownloader(Context context, Fragment frag) {
        super();
        mContext = context;
        whocalledthis = frag;
    }

    @Override
    protected ArrayList<OneSpecificLoc> doInBackground(String... a) {
//Hotmovie때와 달리 Retrofit이 아닌 천연 HttpURLConnection 및 직접 파싱으로 해결

        HttpURLConnection urlConnection = null;
        try {
            String body = "sWideareaCd=" + a[0];

            URL url = new URL("http://www.kobis.or.kr/kobis/business/mast/thea/findBasareaCdList.do");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = urlConnection.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

                String inputLine;

                String firsttoken = "[";
                String secondtoken = "]";
                String cdValue;
                String cdNmValule;

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("[")) {
                        int firsttokenindex = inputLine.indexOf(firsttoken);
                        inputLine = inputLine.substring(firsttokenindex);
                    }
                    if (inputLine.contains("]")) {
                        int secondtokenindex = inputLine.indexOf(secondtoken);
                        inputLine = inputLine.substring(0, secondtokenindex + 1);
                    }

                    try {
                        JSONObject jo = new JSONObject(inputLine);
                        cdValue = jo.getString("cd");
                        cdNmValule = jo.getString("cdNm");
                        AL.add(new OneSpecificLoc(cdValue, cdNmValule));
                    } catch (Exception e) {

                    }
                }
                in.close();
            } else {
                throw new Exception("network error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return AL;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mcL = new CustomLoadingIcon(mContext);
        mcL.show();
    }

    @Override
    protected void onPostExecute(ArrayList<OneSpecificLoc> result) {
        mcL.dismiss();
        ((SpecificLoc) whocalledthis).showSpecificLoc(result);
    }
}

