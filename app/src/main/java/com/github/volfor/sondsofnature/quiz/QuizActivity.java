package com.github.volfor.sondsofnature.quiz;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ActivityQuizBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;
    QuizAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        adapter = new QuizAdapter(getSupportFragmentManager());
        binding.quizPager.setAdapter(adapter);

    }

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

    @Subscribe
    public void onAnsweredEvent(AnswerEvent e) {
        Toast.makeText(this, "check: " + e.answer, Toast.LENGTH_SHORT).show();

        if (e.answer) {
            adapter.addQuizTask();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.quizPager.setCurrentItem(binding.quizPager.getCurrentItem() + 1);
                }
            }, 3000);
        }
    }

}
