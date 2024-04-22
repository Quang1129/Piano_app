package com.example.pianoapp.model;

import android.graphics.RectF;

public class Key {
    // Piano keyview
    public int sound;


    public RectF rect;

    public Key(int sound, RectF rect, boolean down) {
        this.sound= sound;
        this.rect = rect;
        this.down = down;
    }
    public boolean down;


}
