package com.github.volfor.sondsofnature.quiz;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.Utils;
import com.github.volfor.sondsofnature.databinding.ItemQuizCardBinding;
import com.github.volfor.sondsofnature.models.GameCard;
import com.github.volfor.sondsofnature.models.Quiz;

import java.util.List;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class QuizTaskAdapter extends RecyclerView.Adapter<QuizTaskAdapter.ViewHolder> {

    private List<GameCard> cards;
    private Quiz quiz;

    public QuizTaskAdapter(Quiz quiz) {
        this.quiz = quiz;
        cards = quiz.getAllCards();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindQuiz(quiz, cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemQuizCardBinding binding;

        public ViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        void bindQuiz(Quiz quiz, GameCard card) {
            if (quiz.getDifficulty() == Quiz.Difficulty.EXTRA) {
                binding.cardImage.getLayoutParams().height = (int) Utils.pxFromDp(binding.cardImage.getContext(), 96);
            }

            binding.setItem(new QuizItemViewModel(quiz, card));
        }
    }

}
