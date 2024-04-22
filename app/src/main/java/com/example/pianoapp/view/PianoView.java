package com.example.pianoapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.pianoapp.R;
import com.example.pianoapp.model.Key;
import com.example.pianoapp.utils.SoundManager;

import java.util.ArrayList;

public class PianoView extends View {


    public final int NUMBER_OF_KEYS  = 14;
    private ArrayList<Key> whites;
    private ArrayList<Key> blacks;



    Paint blackPen, whitePen, greyPen, lightBlackPen;

    private int keyWidth, keyHeight;
    private boolean isDragging = false;

    private Key mLastKey;

    private SoundManager soundManager;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        whites = new ArrayList<Key>();
        blacks = new ArrayList<Key>();

        whitePen = new Paint();

        whitePen.setColor(Color.WHITE);
        whitePen.setStyle(Paint.Style.FILL );



        blackPen = new Paint();

        blackPen.setColor(Color.BLACK);
        blackPen.setStyle(Paint.Style.FILL );


        lightBlackPen = new Paint();

        lightBlackPen.setColor(Color.rgb(50, 50 , 50));
        lightBlackPen.setStyle(Paint.Style.FILL );


        greyPen = new Paint();

        greyPen.setColor(Color.rgb(225,225,225));
        greyPen.setStyle(Paint.Style.FILL );


        soundManager = SoundManager.getInstance();
        soundManager.init(context);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        keyWidth = w / NUMBER_OF_KEYS;
        keyHeight = h;


        int blackcount = 15;

        // create arrays of white key
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            int left = i * keyWidth;
            int right = left + keyWidth;

            RectF rect = new RectF(left, 0,right, h);
            whites.add(new Key(i + 1, rect, false));

            // create arrays of black key
            if (i !=0 && i!= 3 && i!=7 && i!= 10) {
                rect = new RectF((float)(i-1) *keyWidth + 0.75f * keyWidth ,
                        0,
                        (float) i * keyWidth + 0.25f*keyWidth,
                        0.62f * keyHeight
                );
                blacks.add(new Key(blackcount, rect, false));
                blackcount++;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw white keys first
        for (Key k : whites) {
            canvas.drawRect(k.rect, k.down? greyPen : whitePen);
        }

// Draw black keys second
        for (Key k : blacks) {
            canvas.drawRect(k.rect, k.down? lightBlackPen : blackPen);
        }

        // DRAW OUTLINE OF KEYS TO DIFFERENTIATE BLACK AND WHITE KEYS:
        for (int i = 1; i < NUMBER_OF_KEYS; i++) {
            canvas.drawLine(i * keyWidth, 0, i * keyWidth, keyHeight, blackPen);
        }

    }


    private Key getTouched(float x, float y) {
        for (Key k : blacks) {
            if (k.rect.contains(x, y)) {
                return k;
            }
        }
        for (Key k : whites) {
            if (k.rect.contains(x, y)) {
                return k;
            }
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        boolean isDownAction = action == MotionEvent.ACTION_DOWN
                || action == MotionEvent.ACTION_MOVE;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
                    float x = event.getX(touchIndex);
                    float y = event.getY(touchIndex);

                    Key touchedKey = getTouched(x, y);

                    // Check for black key touch
                    for (Key k : blacks) {
                        if (k.rect.contains(x, y)) {
                            k.down = isDownAction;
                            switch (k.sound) {
                                case 15:
                                    soundManager.playSound(R.raw.db3);
                                    isDragging = true;
                                    mLastKey = k;
                                    break;
                                case 16:
                                    soundManager.playSound(R.raw.eb3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 17:
                                    soundManager.playSound(R.raw.gb3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 18:
                                    soundManager.playSound(R.raw.ab3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 19:
                                    soundManager.playSound(R.raw.bb3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 20:
                                    soundManager.playSound(R.raw.db4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 21:
                                    soundManager.playSound(R.raw.eb4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 22:
                                    soundManager.playSound(R.raw.gb4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 23:
                                    soundManager.playSound(R.raw.ab4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 24:
                                    soundManager.playSound(R.raw.bb4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                            }
                            invalidate();
                            return true;
                        }
                    }

                    // Check for white key touch
                    for (Key k : whites) {
                        if (k.rect.contains(x, y)) {
                            k.down = isDownAction;

                            switch (k.sound) {
                                case 1:
                                    soundManager.playSound(R.raw.c2);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 2:
                                    soundManager.playSound(R.raw.d2);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 3:

                                    soundManager.playSound(R.raw.e2);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 4:
                                    soundManager.playSound(R.raw.f3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 5:
                                    soundManager.playSound(R.raw.g3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 6:
                                    soundManager.playSound(R.raw.a3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 7:
                                    soundManager.playSound(R.raw.b3);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 8:
                                    soundManager.playSound(R.raw.c4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 9:
                                    soundManager.playSound(R.raw.d4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 10:
                                    soundManager.playSound(R.raw.e4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 11:
                                    soundManager.playSound(R.raw.f4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 12:
                                    soundManager.playSound(R.raw.g4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 13:
                                    soundManager.playSound(R.raw.a4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                                case 14:
                                    soundManager.playSound(R.raw.b4);
                                    isDragging = true;
                                    mLastKey = k;

                                    break;
                            }
                            invalidate();
                            return true;
                        }
                    }
                    break;
                }
            case MotionEvent.ACTION_UP:
                for (Key k : blacks) {
                    if (k.down) {
                        k.down = false;
                        isDragging = false;
                        invalidate();
                    }
                }
                for (Key k : whites) {
                    if (k.down) {
                        k.down = false;
                        isDragging = false;
                        invalidate();
                    }
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    for (Key k : blacks) {
                        if (k.rect.contains((int) event.getX(), (int) event.getY())) {
                            if (k != mLastKey) {
                                mLastKey.down = false;
                                mLastKey = k;
                                k.down = true;
                                switch (k.sound) {
                                    case 15:
                                        soundManager.playSound(R.raw.db3);
                                        isDragging = true;
                                        mLastKey = k;
                                        break;
                                    case 16:
                                        soundManager.playSound(R.raw.eb3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 17:
                                        soundManager.playSound(R.raw.gb3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 18:
                                        soundManager.playSound(R.raw.ab3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 19:
                                        soundManager.playSound(R.raw.bb3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 20:
                                        soundManager.playSound(R.raw.db4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 21:
                                        soundManager.playSound(R.raw.eb4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 22:
                                        soundManager.playSound(R.raw.gb4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 23:
                                        soundManager.playSound(R.raw.ab4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 24:
                                        soundManager.playSound(R.raw.bb4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                }
                                invalidate();
                            }
                        }
                    }

                    for (Key k : whites) {
                        if (k.rect.contains((int) event.getX(), (int) event.getY())) {
                            if (k != mLastKey) {
                                mLastKey.down = false;
                                mLastKey = k;
                                k.down = true;
                                switch (k.sound) {
                                    case 1:
                                        soundManager.playSound(R.raw.c2);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 2:
                                        soundManager.playSound(R.raw.d2);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 3:

                                        soundManager.playSound(R.raw.e2);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 4:
                                        soundManager.playSound(R.raw.f3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 5:
                                        soundManager.playSound(R.raw.g3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 6:
                                        soundManager.playSound(R.raw.a3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 7:
                                        soundManager.playSound(R.raw.b3);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 8:
                                        soundManager.playSound(R.raw.c4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 9:
                                        soundManager.playSound(R.raw.d4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 10:
                                        soundManager.playSound(R.raw.e4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 11:
                                        soundManager.playSound(R.raw.f4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 12:
                                        soundManager.playSound(R.raw.g4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 13:
                                        soundManager.playSound(R.raw.a4);
                                        isDragging = true;
                                        mLastKey = k;

                                        break;
                                    case 14:
                                        soundManager.playSound(R.raw.b4);
                                        isDragging = true;
                                        mLastKey = k;
                                        break;
                                }
                                invalidate();
                            }
                        }
                    }
                }
            break;
        }

        return super.onTouchEvent(event);
    }
}
