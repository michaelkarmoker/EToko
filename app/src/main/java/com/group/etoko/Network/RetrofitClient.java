package com.group.etoko.Network;

import com.group.etoko.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static  Retrofit get() {
        if (retrofit == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new QueryParameterInterceptor());

                    OkHttpClient client = httpClient.build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
        }
        return retrofit;
    }

    public static Retrofit noInterceptor() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
