package com.kegarlv.lostfilm.Rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ivan on 04.02.17.
 */

public class ApiClient {
    public static final String BASE_URL = "https://www.lostfilm.tv";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
