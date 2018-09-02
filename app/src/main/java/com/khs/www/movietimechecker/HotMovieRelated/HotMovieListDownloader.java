package com.khs.www.movietimechecker.HotMovieRelated;

import android.content.Context;
import android.widget.Toast;

import com.khs.www.movietimechecker.ETC.CustomLoadingIcon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//Retrofit, Jsoup으로 인기영화 리스트를 크롤링, 파싱하는 클래스
public class HotMovieListDownloader {

    String errMessage = "오류가 발생했습니다, 네트워크 연결 등을 확인하세요";

    ArrayList<HotMovieItem> movieArray = new ArrayList<>(); //영화 목록
    Context mContext;
    CustomLoadingIcon mcL;
    Retrofit retrofit;
    HotMovieInterface service;


    public HotMovieListDownloader(Context context) {
        super();
        mContext = context;
        retrofit = new Retrofit.Builder().baseUrl("https://movie.naver.com/movie/running/").build();
        service = retrofit.create(HotMovieInterface.class);
        mcL = new CustomLoadingIcon(mContext);
    }

    //네이버 현재 인기상영작 제목 크롤링
    public void getHotMovieList() {

        mcL.show();

        Call<ResponseBody> request = service.getHotMovieList();
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Document doc = Jsoup.parse(response.body().string());
                        Elements arr = doc.body().select("[data-title]");
                        Iterator it = arr.iterator();
                        while (it.hasNext()) {
                            Element e = (Element) it.next();
                            String movieTitle = e.attr("data-title");
                            String posterLink = e.getElementsByTag("img").attr("src");
                            HotMovieItem oneItem = new HotMovieItem(movieTitle, posterLink);
                            movieArray.add(oneItem);
                        }
                        ((HotMovie) mContext).showMovieTitle(movieArray);
                        mcL.dismiss();
                    } catch (Exception e) {
                        Toast.makeText(mContext, errMessage, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(mContext, errMessage, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, errMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
