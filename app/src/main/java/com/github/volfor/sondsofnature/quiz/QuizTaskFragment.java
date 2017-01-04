package com.github.volfor.sondsofnature.quiz;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_task, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Quiz quiz = new Quiz();
        quiz.create(getContext(), 0);

        QuizTaskAdapter adapter = new QuizTaskAdapter(quiz);

        binding.quizList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.quizList.setAdapter(adapter);
    }

}
