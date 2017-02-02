package com.duk.lab.android.connection.retrofit;

import java.io.IOException;
import java.net.HttpURLConnection;

import android.os.AsyncTask;

import com.duk.lab.android.connection.retrofit.services.GithubMain;
import com.google.gson.Gson;

import retrofit.RestAdapter;
import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.GsonConverter;



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
            RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder();
            restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("https://api.github.com")
                    .setConverter(new GsonConverter(new Gson()))
                    .setClient(new UrlConnectionClient() {

                        @Override
                        protected HttpURLConnection openConnection(Request request) throws IOException {
                            return super.openConnection(request);
                        }
                    });
            RestAdapter restAdapter = restAdapterBuilder.build();
            GithubMain githubMain = restAdapter.create(GithubMain.class);

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
