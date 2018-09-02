package com.khs.www.movietimechecker.FinalSelectionRelated;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.khs.www.movietimechecker.ETC.CustomLoadingIcon;
import com.khs.www.movietimechecker.FindTheaterRelated.OneTheaterInfo;
import com.khs.www.movietimechecker.HotMovieRelated.HotMovieItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.khs.www.movietimechecker.ETC.Util.arrayToString;

//마지막 최종 결과 화면을 위한 AsyncTask 다운로더
public class FinalListDownloader extends AsyncTask<Void, Void, ArrayList<OneFinalItem>> {

    ArrayList<HotMovieItem> selectedMovieTitleArrForFinalSelection;//선택된 영화 제목들
    ArrayList<OneTheaterInfo> selectedTheaterArrForFinalSelection;//선택된 영화관 정보들

    String date = "";//관람 날짜
    String time = "";//관람 시간

    CustomLoadingIcon mcL;
    ArrayList<OneFinalItem> finalAL = new ArrayList<>();

    Context mContext;

    public FinalListDownloader(ArrayList<HotMovieItem> a, ArrayList<OneTheaterInfo> b, String c, String d, Context context) {
        super();
        selectedMovieTitleArrForFinalSelection = a;
        selectedTheaterArrForFinalSelection = b;
        date = c;
        time = d;
        mContext = context;
    }


    @Override
    protected ArrayList<OneFinalItem> doInBackground(Void... a) {

        HttpURLConnection urlConnection = null;

        try {

            for (int i = 0; i < selectedTheaterArrForFinalSelection.size(); i++) {

                String resultString = "";//한 영화관의 상영정보
                boolean flag = false;

                String body = "theaCd=" + selectedTheaterArrForFinalSelection.get(i).getCode() + "&" + "showDt=" + date;
                URL url = new URL("http://www.kobis.or.kr/kobis/business/mast/thea/findSchedule.do");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                OutputStream os = urlConnection.getOutputStream();
                os.write(body.getBytes());
                os.flush();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

                    String inputLine;
                    String firsttoken = "\"schedule\" :[";
                    String secondtoken = "]";

                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("\"schedule\" :[")) {
                            int firsttokenindex = inputLine.indexOf(firsttoken);
                            inputLine = inputLine.substring(firsttokenindex + inputLine.length() - 1);
                            flag = true;
                        }
                        if (inputLine.contains("]") && (inputLine.equals("],") == false) && (inputLine.contains("]\"") == false)) {//끝 토큰을 잘 결정해야함 _ 비슷한 모양 많음
                            int secondtokenindex = inputLine.indexOf(secondtoken);
                            inputLine = inputLine.substring(0, secondtokenindex + 1);
                            resultString += inputLine;
                            flag = false;
                        }
                        if (flag == true) {
                            resultString += inputLine;
                        }
                    }
//한 영화관의 상영정보
                    JSONArray temparr = new JSONArray(resultString);

// 영화 제목, 영화관 이름, 영화관 내 시간 테이블
                    String mtforoneitem, tnforoneitem, tbforoneitem;

//한 영화관 총 시간표
                    for (int j = 0; j < temparr.length(); j++) {
//하나의 요소(영화관, 영화제목, 시간들)
                        JSONObject onejsonobject = temparr.getJSONObject(j);

//선택된 영화제목들
                        for (int z = 0; z < selectedMovieTitleArrForFinalSelection.size(); z++) {

                            if (onejsonobject.getString("movieNm").replaceAll(" ", "").contains(selectedMovieTitleArrForFinalSelection.get(z).getTitle().replaceAll(" ", ""))) {

                                ArrayList<String> selectedTimeTable = new ArrayList<String>();
//시간 정보를 배열로 전환
                                String[] temptimetable = onejsonobject.getString("showTm").split(",");

                                mtforoneitem = onejsonobject.getString("movieNm");
                                tnforoneitem = onejsonobject.getString("scrnNm");// ?관

                                for (int h = 0; h < temptimetable.length; h++) {
//검색 시간 이후 여부 체크
                                    if (Integer.parseInt(time) <= Integer.parseInt(temptimetable[h])) {
                                        String hour = temptimetable[h].substring(0, 2);
                                        String minute = temptimetable[h].substring(2);
//해당되는 시간테이블에 추가
                                        selectedTimeTable.add(hour + ":" + minute);
                                    }
                                }
//이를 다시 문자열로 변환
                                tbforoneitem = arrayToString(selectedTimeTable, " | ");

//해당하는 영화시간이 하나라도 있는 경우에만
                                if (tbforoneitem.length() != 0) {
                                    OneFinalItem oneEntry = new OneFinalItem(mtforoneitem, selectedTheaterArrForFinalSelection.get(i).getName(), tbforoneitem, tnforoneitem);
                                    finalAL.add(oneEntry);
                                }
                            }
                        }
                    }
                    in.close();
                } else {
                    throw new Exception("network error");
                }
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return finalAL;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mcL = new CustomLoadingIcon(mContext);
        mcL.show();
    }

    @Override
    protected void onPostExecute(ArrayList<OneFinalItem> result) {
        mcL.dismiss();
        List<Fragment> LF = ((Finalselection) mContext).getSupportFragmentManager().getFragments();
        for (Fragment a : LF) {
            if (a instanceof FinalTitleSortFrag) {
                ((FinalTitleSortFrag) a).showFinalTitleCenteredList(finalAL);
            } else if (a instanceof FinalTheaterSortFrag) {
                ((FinalTheaterSortFrag) a).showFinalTheaterCenteredList(finalAL);
            }
        }
    }
}

