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
import com.github.volfor.sondsofnature.databinding.ChooseModeDialogBinding;
import com.github.volfor.sondsofnature.databinding.CongratsDialogBinding;
import com.github.volfor.sondsofnature.learning.LearningActivity;
import com.github.volfor.sondsofnature.quiz.QuizActivity;


public class MainActivity extends AppCompatActivity {

    public static final int ANIMALS = 546;
    public static final int TRANSPORT = 547;

    public static final int QUIZ_REQ_CODE = 418;

    private ActivityMainBinding binding;
    private AlertDialog chooseModeDialog;

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
        showChooseModeDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QUIZ_REQ_CODE && resultCode == RESULT_OK) {
            showCongratsDialog();
        }
    }

    private void showChooseModeDialog() {
        ChooseModeDialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.choose_mode_dialog, null, false);
        binding.setModel(this);

        chooseModeDialog = new AlertDialog.Builder(this)
                .setView(binding.getRoot())
                .show();
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

    public void onAnimalsQuizClick(View v) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("type", ANIMALS);

        startActivityForResult(intent, QUIZ_REQ_CODE);
        chooseModeDialog.dismiss();
    }

    public void onTransportQuizClick(View v) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("type", TRANSPORT);

        startActivityForResult(intent, QUIZ_REQ_CODE);
        chooseModeDialog.dismiss();
    }

    public void onComplexQuizClick(View v) {
        startActivityForResult(new Intent(MainActivity.this, QuizActivity.class), QUIZ_REQ_CODE);
        chooseModeDialog.dismiss();
    }

}
