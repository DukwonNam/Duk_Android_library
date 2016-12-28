package com.duk.lab.android.connection;

import android.os.AsyncTask;

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
            return mUrlString;
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
