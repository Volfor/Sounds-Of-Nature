package com.github.volfor.sondsofnature.quiz;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private Quiz quiz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_task, container, false);
        binding.setModel(this);

        quiz = Quiz.getInstance();
        if (quiz.isFinished()) {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
            return null;
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quiz.createTask(getContext(), (int) getArguments().get("type"));

        QuizTaskAdapter adapter = new QuizTaskAdapter(quiz);
        GridLayoutManager lm;

        if (adapter.getItemCount() % 2 == 0) {
            lm = new GridLayoutManager(getContext(), 2);
        } else {
            lm = new GridLayoutManager(getContext(), 3);
        }

        binding.quizList.setLayoutManager(lm);
        binding.quizList.setAdapter(adapter);
    }

    public void onReplayClick(View v) {
        Quiz.getInstance().replay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Quiz.getInstance().clear();
    }

}
