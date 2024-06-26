package com.example.pianoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.util.SparseIntArray;

import androidx.activity.result.ActivityResult;

import com.example.pianoapp.R;

public class SoundManager {
    private SoundPool mSoundPool;
    private SparseIntArray mSoundPoolMap;
    private boolean mMute = false;

    private Context mContext;
    private static final int MAX_STREAM = 10;

    private static final int STOP_DELAY_MILLIS = 800;

    private Handler mHandler;

    private static SoundManager instance = null;

    private SoundManager() {
        mSoundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new SparseIntArray();
        mHandler = new Handler();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }

        return instance;
    }

    public void initStreamTypeMedia(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }


    public void addSound(int soundID) {
        mSoundPoolMap.put(soundID, mSoundPool.load(mContext, soundID, 1));
    }


    public void playSound(int soundID) {
        if (mMute) {
            return;
        }

        boolean hasSound = mSoundPoolMap.indexOfKey(soundID) >= 0;
        if (!hasSound) {
            return;
        }
        final int soundId = mSoundPool.play(mSoundPoolMap.get(soundID), 1,1, 1, 0, 1f);
        scheduleSoundStop(soundId);
    }


    private void scheduleSoundStop(final int soundId) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSoundPool.stop(soundId);
            }
        }, STOP_DELAY_MILLIS);
    }

    public void init(Context context) {
        this.mContext = context;

        instance.initStreamTypeMedia((Activity) mContext);

        instance.addSound(R.raw.c2);
        instance.addSound(R.raw.c3);
        instance.addSound(R.raw.c4);
        instance.addSound(R.raw.d2);
        instance.addSound(R.raw.d3);
        instance.addSound(R.raw.d4);
        instance.addSound(R.raw.e2);
        instance.addSound(R.raw.e3);
        instance.addSound(R.raw.e4);
        instance.addSound(R.raw.f3);
        instance.addSound(R.raw.f4);
        instance.addSound(R.raw.a3);
        instance.addSound(R.raw.a4);
        instance.addSound(R.raw.b3);
        instance.addSound(R.raw.b4);
        instance.addSound(R.raw.db3);
        instance.addSound(R.raw.db4);
        instance.addSound(R.raw.eb3);
        instance.addSound(R.raw.eb4);
        instance.addSound(R.raw.gb3);
        instance.addSound(R.raw.gb4);
        instance.addSound(R.raw.ab3);
        instance.addSound(R.raw.ab4);
        instance.addSound(R.raw.bb3);
        instance.addSound(R.raw.bb4);
        instance.addSound(R.raw.g3);
        instance.addSound(R.raw.g4);

    }


}
