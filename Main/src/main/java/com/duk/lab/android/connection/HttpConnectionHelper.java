package com.duk.lab.android.connection;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Duk on 2016-12-22.
 */

public class HttpConnectionHelper {
    interface HttpConnectionListener {
        void onReceiveString(String result);
    }

    private final HttpConnectionListener mListener;

    public HttpConnectionHelper(HttpConnectionListener listener) {
        mListener = listener;
    }

    public void requestUrl(String urlString) {
        new HttpAsyncTask(urlString).execute();
    }

    class HttpAsyncTask extends AsyncTask<Void, Void, String> {
        private final String mUrlString;

        HttpAsyncTask(String urlString) {
            mUrlString = urlString;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = mUrlString;
            try {
                final URL url = new URL(mUrlString);
                final URLConnection urlConnection = url.openConnection();
                urlConnection.connect();

                final BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                final StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (mListener != null) {
                mListener.onReceiveString(result);
            }
        }
    }
}
