package com.group.etoko.Network;

import androidx.annotation.NonNull;

import com.group.etoko.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class QueryParameterInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("choloshop_api", BuildConfig.APP_API)
                .build();

        Request request = chain.request().newBuilder()
                // .addHeader("Authorization", "Bearer token")
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
