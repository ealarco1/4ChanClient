package com.ealarcon.app.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class that encapsulates retrofit client creation
 * Created by ealarcon on 6/22/17.
 */

public class ServiceFactory {

    public static class Builder {

        private String mBaseUrl;
        private int mTimeoutInSeconds;

        public Builder() {
            this.mTimeoutInSeconds = 0;
        }

        public ServiceFactory.Builder withBaseUrl(String baseUrl) {
            this.mBaseUrl = baseUrl;
            return this;
        }

        public ServiceFactory.Builder withTimeout(int timeInSeconds) {
            this.mTimeoutInSeconds = timeInSeconds;
            return this;
        }

        public <T> T buildService(final Class<T> serviceClass) {
            return createService(serviceClass);
        }

        private OkHttpClient createClient() {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

            if (mTimeoutInSeconds > 0) {
                clientBuilder.connectTimeout(mTimeoutInSeconds, TimeUnit.SECONDS);
                clientBuilder.readTimeout(mTimeoutInSeconds, TimeUnit.SECONDS);
            }
            return clientBuilder.build();
        }

        private <T> T createService(final Class<T> serviceClass) {
            return new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(createClient())
                    .build()
                    .create(serviceClass);
        }
    }
}
