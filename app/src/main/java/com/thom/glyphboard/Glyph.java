package com.thom.glyphboard;

import android.app.Instrumentation;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_DEL;

public class Glyph extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private  KeyboardView kv;
    private Keyboard keyboard;

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(2,0);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                Log.i("test", new String(Character.toChars(primaryCode)));
                Log.i("test", new String(Character.toChars(primaryCode)));
                ic.commitText(new String(Character.toChars(primaryCode)), 1);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
        }
    }

    @Override
    public View onCreateInputView() {

        final Context here = this;

        final LinearLayout view = (LinearLayout) View.inflate(here, R.layout.scroll_view, null);
        ScrollView scrollView = view.findViewById(R.id.keyboard_scroll);
        View delButton = view.findViewById(R.id.delButton);
        View enterButton = view.findViewById(R.id.enterButton);
        View page1Button = view.findViewById(R.id.page1Button);
        View page2Button = view.findViewById(R.id.page2Button);
        View page3Button = view.findViewById(R.id.page3Button);
        View page4Button = view.findViewById(R.id.page4Button);
        View page5Button = view.findViewById(R.id.page5Button);
        View page6Button = view.findViewById(R.id.page6Button);
        View page7Button = view.findViewById(R.id.page7Button);
        View page8Button = view.findViewById(R.id.page8Button);
        View page9Button = view.findViewById(R.id.page9Button);


        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(here, R.xml.page_1);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        kv.setPreviewEnabled(false);

        page1Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_1));
            }
        });
        page2Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_2));
            }
        });
        page3Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_3));
            }
        });
        page4Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_4));
            }
        });
        page5Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_5));
            }
        });
        page6Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_6));
            }
        });
        page7Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_7));
            }
        });
        page8Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_8));
            }
        });
        page9Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                kv.setKeyboard(new Keyboard(here, R.xml.page_9));
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputConnection ic = getCurrentInputConnection();
                ic.deleteSurroundingText(2,0);
            }
        });
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputConnection ic = getCurrentInputConnection();
                ic.sendKeyEvent(new KeyEvent(ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            }
        });

        scrollView.addView(kv);
        return view;
    }
}
