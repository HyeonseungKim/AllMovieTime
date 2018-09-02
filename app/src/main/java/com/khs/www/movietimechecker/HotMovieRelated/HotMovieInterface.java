package com.khs.www.movietimechecker.HotMovieRelated;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotMovieInterface {
    @GET("current.nhn?view=list&tab=normal&order=reserve")
    Call<ResponseBody> getHotMovieList();
}
