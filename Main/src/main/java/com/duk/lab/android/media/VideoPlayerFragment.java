package com.duk.lab.android.media;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.duk.lab.android.R;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class VideoPlayerFragment extends Fragment implements View.OnClickListener {
    private static final String VIDEO_SOURCE_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    private VideoView mVideoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.media_video_player, container, false);

        mVideoView = (VideoView)view.findViewById(R.id.videoView);
        mVideoView.setVideoURI(Uri.parse(VIDEO_SOURCE_URL));

        MediaController mediaController = new MediaController(getActivity());
        mVideoView.setMediaController(mediaController);
        mVideoView.setOnCompletionListener(mCompletionListener);
        mVideoView.start();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
        }
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {

        }
    };
}
