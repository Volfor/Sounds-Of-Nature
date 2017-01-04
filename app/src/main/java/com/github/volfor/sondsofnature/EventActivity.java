package com.github.volfor.sondsofnature;

import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class EventActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
