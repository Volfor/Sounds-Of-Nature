package com.github.volfor.sondsofnature.quiz;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ActivityQuizBinding;
import com.github.volfor.sondsofnature.databinding.WrongAnswerDialogBinding;
import com.github.volfor.sondsofnature.events.AnswerEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;
    QuizPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        adapter = new QuizPagerAdapter(getSupportFragmentManager());
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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.addQuizTask();
                    binding.quizPager.setCurrentItem(binding.quizPager.getCurrentItem() + 1);
                }
            }, 1000);
        } else {
            showWrongAnswerDialog(e.correctAnswer.getImage());
        }
    }

    private void showWrongAnswerDialog(@DrawableRes int correctImage) {
        WrongAnswerDialogBinding binding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.wrong_answer_dialog, null, false);

        Glide.with(this)
                .load(correctImage)
                .into(binding.correctImage);

        new AlertDialog.Builder(this)
                .setView(binding.getRoot())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

}
