package com.duk.lab.android.connection.retrofit;

import android.os.AsyncTask;

import com.duk.lab.android.connection.retrofit.services.GithubMain;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Duk on 2016-12-22.
 */

public class RetrofitConnectionHelper {
    interface RetrofitConnectionListener {
        void onReceiveData(Object obj);
    }

    private final RetrofitConnectionListener mListener;

    public RetrofitConnectionHelper(RetrofitConnectionListener listener) {
        mListener = listener;
    }

    public void requestService() {
        new RetrofitAsyncTask().execute();
    }

    class RetrofitAsyncTask extends AsyncTask<Void, Void, Object> {

        @Override
        protected Object doInBackground(Void... params) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            builder.build();

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build());
            Retrofit retrofit = retrofitBuilder.build();
            GithubMain githubMain = retrofit.create(GithubMain.class);

            return githubMain.getGithubMain();
        }

        @Override
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (mListener != null) {
                mListener.onReceiveData(obj);
            }
        }
    }
}
