package com.cut2it.cut2itapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cut2it.cut2itapp.avcommunication.AVCommunication;
import com.cut2it.cut2itapp.rangeseekbar.RangeSeekBar;
import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerViewActivity extends AppCompatActivity {
    private PlayerView exoPlayerView;
    private ImageButton mPlayBtn, mPauseBtn, mReplayBtn;
    private SeekBar mSeekBar;
    private  TextView mCurrentTimeTextView, mVideoDuration;
    private LinearLayout mMediaControllers;
    //added for library ,library to be included in other project.
    private  int videoID;

    private RangeSeekBar mRangeSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerview);

        mPlayBtn = findViewById(R.id.btnPlay);
        mPauseBtn = findViewById(R.id.btnPause);
        mReplayBtn = findViewById(R.id.btnReplay);
        mSeekBar = findViewById(R.id.seekBar);
        mCurrentTimeTextView = findViewById(R.id.currentTimeTextView);
        mVideoDuration = findViewById(R.id.videoDuration);
        mMediaControllers = findViewById(R.id.mediaControllers);
        mRangeSeekbar = findViewById(R.id.rangeSeekBar);


        AVCommunication avCommunication = new AVCommunication(this);


        exoPlayerView = findViewById(R.id.exoplayer);
        exoPlayerView.setUseController(false);

        avCommunication.makeStream(exoPlayerView);
//        avCommunication.setSeekBar(mSeekBar);
        avCommunication.setTimeLabel(mCurrentTimeTextView, mVideoDuration);

//        avCommunication.setRangeSeekbar(mRangeSeekbar);
//        avCommunication.playSegment(4l,20l);
        avCommunication.playSegmentSimple(mSeekBar,4,7);

        avCommunication.setVideoID(videoID);
        avCommunication.getVideoID();
        mPlayBtn.setOnClickListener(view -> {
            mPauseBtn.setVisibility(View.VISIBLE);
            mPlayBtn.setVisibility(View.GONE);
            avCommunication.play();

        });
        mPauseBtn.setOnClickListener(view -> {
            avCommunication.pause();
            mPauseBtn.setVisibility(View.GONE);
            mPlayBtn.setVisibility(View.VISIBLE);
        });


        mReplayBtn.setOnClickListener(view -> avCommunication.replay());


    }
}
