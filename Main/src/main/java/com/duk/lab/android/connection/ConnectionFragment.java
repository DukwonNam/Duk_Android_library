package com.duk.lab.android.connection;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duk.lab.android.R;
import com.duk.lab.android.connection.retrofit.RetrofitActivity;
import com.duk.lab.android.util.CommonUtil;

/**
 * Created by Duk on 2016-12-19.
 */

public class ConnectionFragment extends Fragment implements View.OnClickListener {
    private static final int[] CLICK_EVENT_ID_ARRAY = new int[] {
            R.id.url1,
            R.id.url2,
            R.id.retrofit
    };

    private HttpConnectionHelper mHttpConnectionHelper;
    private TextView mResultView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.connection_main, container, false);
        for (int resId : CLICK_EVENT_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mResultView = (TextView) view.findViewById(R.id.resultText);
        mHttpConnectionHelper = new HttpConnectionHelper(mHttpConnectionListener);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        final CharSequence urlStr = ((TextView)v).getText();
        if (TextUtils.isEmpty(urlStr)) {
            return;
        }

        switch (v.getId()) {
            case R.id.url1:
                mHttpConnectionHelper.requestUrl("https://api.github.com");
                break;
            case R.id.url2:
                mHttpConnectionHelper.requestUrl("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=59\\&gridy=127");
                break;
            case R.id.retrofit:
                CommonUtil.jumpToActivity(getActivity(), RetrofitActivity.class);
                break;
        }
    }

    private HttpConnectionHelper.HttpConnectionListener mHttpConnectionListener = new HttpConnectionHelper.HttpConnectionListener() {

        @Override
        public void onReceiveString(String result) {
            mResultView.setText(result);
        }
    };
}
