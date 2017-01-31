package com.duk.lab.android.connection.retrofit;

import com.duk.lab.android.R;
import com.duk.lab.android.connection.HttpConnectionHelper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Duk on 2016-12-19.
 */

public class RetrofitFragment extends Fragment implements View.OnClickListener {
    private static final int[] CLICK_EVENT_ID_ARRAY = new int[] {
            R.id.url1
    };

    private TextView mResultView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.retrofit_main, container, false);
        for (int resId : CLICK_EVENT_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mResultView = (TextView) view.findViewById(R.id.resultText);

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
                break;
        }
    }
}
