package com.github.volfor.sondsofnature.quiz;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.github.volfor.sondsofnature.EventActivity;
import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ActivityQuizBinding;
import com.github.volfor.sondsofnature.databinding.WrongAnswerDialogBinding;
import com.github.volfor.sondsofnature.events.AnswerEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class QuizActivity extends EventActivity {

    private ActivityQuizBinding binding;
    private QuizPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        setSupportActionBar(binding.include.toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        int type = getIntent().getExtras() == null ? 0 : (int) getIntent().getExtras().get("type");

        adapter = new QuizPagerAdapter(getSupportFragmentManager(), type);
        binding.quizPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onAnsweredEvent(AnswerEvent e) {
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
        WrongAnswerDialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.wrong_answer_dialog, null, false);

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
