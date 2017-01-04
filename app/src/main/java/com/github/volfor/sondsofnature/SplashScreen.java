package com.github.volfor.sondsofnature;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.volfor.sondsofnature.databinding.SplashScreenBinding;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class SplashScreen extends AppCompatActivity {

    private final int DISPLAY_LENGTH = 1500;

    private SplashScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, DISPLAY_LENGTH);
    }

}
