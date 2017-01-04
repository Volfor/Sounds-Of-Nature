package com.github.volfor.sondsofnature.quiz;

import com.github.volfor.sondsofnature.models.GameCard;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class AnswerEvent {

    public boolean answer;
    public GameCard correctAnswer;

    public AnswerEvent(boolean answer) {
        this.answer = answer;
    }

    public AnswerEvent(boolean answer, GameCard correctAnswer) {
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

}
