package com.github.volfor.sondsofnature.root;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ActivityMainBinding;
import com.github.volfor.sondsofnature.databinding.CongratsDialogBinding;
import com.github.volfor.sondsofnature.learning.LearningActivity;
import com.github.volfor.sondsofnature.quiz.QuizActivity;


public class MainActivity extends AppCompatActivity {

    public static final int QUIZ_REQ_CODE = 418;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(this);

        setSupportActionBar(binding.include.toolbar);
    }

    public void onLearnButtonClick(View v) {
        startActivity(new Intent(MainActivity.this, LearningActivity.class));
    }

    public void onQuizButtonClick(View v) {
        startActivityForResult(new Intent(MainActivity.this, QuizActivity.class), QUIZ_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QUIZ_REQ_CODE && resultCode == RESULT_OK) {
            showCongratsDialog();
        }
    }

    private void showCongratsDialog() {
        CongratsDialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.congrats_dialog, null, false);

        new AlertDialog.Builder(this)
                .setView(binding.getRoot())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

}
