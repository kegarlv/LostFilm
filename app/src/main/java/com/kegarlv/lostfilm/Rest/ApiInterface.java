package com.kegarlv.lostfilm.Rest;

import com.kegarlv.lostfilm.Model.LoginResponse;
import com.kegarlv.lostfilm.Model.RssResponse;
import com.squareup.okhttp.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivan on 04.02.17.
 */

public interface ApiInterface {

    @GET("/rss.xml")
    Call<RssResponse> getFeed();

    @GET("/ajaxik.php?act=users&type=login&rem=0")
    Call<LoginResponse> login(@Query("mail") String email, @Query("pass") String password);
}
