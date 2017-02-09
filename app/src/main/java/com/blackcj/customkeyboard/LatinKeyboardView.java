
/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blackcj.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;

import java.util.List;

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;

    static private int alpha = 66;

    public static LatinKeyboardView latinKeyboardView;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        latinKeyboardView = this;
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        latinKeyboardView = this;
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        /*} else if (key.codes[0] == 113) {

            return true; */
        } else {
            //Log.d("LatinKeyboardView", "KEY: " + key.codes[0]);
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    public static void changeAlpha(double delta) {
        alpha += delta;

        alpha = Math.min(alpha, 125);

        if (alpha < 0)
            alpha = 0;

        LatinKeyboardView.latinKeyboardView.postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("test", "onDraw cool calleds");

        Paint paint = new Paint();


        if (alpha >= 66) {
            paint.setColor(Color.RED);
            paint.setAlpha(alpha);
        }
        else {
            paint.setColor(Color.BLUE);
            paint.setAlpha(66 - alpha);

        }

        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), paint);

        paint.setColor(Color.BLACK);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(28);
        paint.setAlpha(255);

//        List<Key> keys = getKeyboard().getKeys();
//        for(Key key: keys) {
//            if(key.label != null) {
//                if (key.label.equals("q")) {
//                    canvas.drawText("1", key.x + (key.width - 25), key.y + 40, paint);
//                } else if (key.label.equals("w")) {
//                    canvas.drawText("2", key.x + (key.width - 25), key.y + 40, paint);
//                } else if (key.label.equals("e")) {
//                    canvas.drawText("3", key.x + (key.width - 25), key.y + 40, paint);
//                } else if (key.label.equals("r")) {
//                    canvas.drawText("4", key.x + (key.width - 25), key.y + 40, paint);
//                } else if (key.label.equals("t")) {
//                    canvas.drawText("5", key.x + (key.width - 25), key.y + 40, paint);
//                }
//            }

//        }
    }
}