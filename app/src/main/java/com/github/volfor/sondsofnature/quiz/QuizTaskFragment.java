package com.github.volfor.sondsofnature.quiz;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.FragmentQuizTaskBinding;
import com.github.volfor.sondsofnature.models.Quiz;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class QuizTaskFragment extends Fragment {

    private FragmentQuizTaskBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_task, container, false);
        binding.setModel(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Quiz quiz = Quiz.getInstance();
        if (quiz.isFinished()) {
            // show congrats
            new AlertDialog.Builder(getContext())
                    .setTitle("Congratz!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    })
                    .show();
            return;
        }

        quiz.createTask(getContext());

        QuizTaskAdapter adapter = new QuizTaskAdapter(quiz);

        binding.quizList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.quizList.setAdapter(adapter);
    }

    public void onReplayClick(View v) {
        Quiz.getInstance().replay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Quiz.clear();
    }

}
