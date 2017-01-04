package com.github.volfor.sondsofnature.models;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.github.volfor.sondsofnature.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class Quiz {

    private int difficulty = 0; //1, 2, 3

    private GameCard correctCard;
    private List<GameCard> wrongCards;

//    private void generate(Context context) {
//        List<GameCard> animals = Utils.getAnimalCards(context);
//
//        correctIndex = new Random().nextInt(animals.size());
//        correctCard = animals.get(correctIndex);
//
//        wrongCards = new ArrayList<>();
//
//        int wrongIndex = new Random().nextInt(animals.size());
//        while (wrongIndex == correctIndex) {
//            wrongIndex = new Random().nextInt(animals.size());
//        }
//
//        wrongCards.add(animals.get(wrongIndex));
//    }

    public void create(Context context, int difficulty) {
        List<GameCard> animals = Utils.getAnimalCards(context);
        Collections.shuffle(animals);

        correctCard = animals.get(0);
        wrongCards = new ArrayList<>();

        switch (difficulty) {
            case 2:
                wrongCards.add(animals.get(1));
            case 1:
                wrongCards.add(animals.get(2));
            case 0:
                wrongCards.add(animals.get(3));
                break;
            default:
        }

        start(context);
    }

    public void start(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MediaPlayer player = MediaPlayer.create(context, correctCard.getSounds()
                        .get(new Random().nextInt(correctCard.getSounds().size())));
                player.start();
            }
        }, 500);
    }

    public boolean check(GameCard answer) {
        return answer.getName().equals(correctCard.getName());
    }

    public boolean check2(GameCard answer) {
        return answer.equals(correctCard);
    }

    public GameCard getCorrectCard() {
        return correctCard;
    }

    public List<GameCard> getWrongCards() {
        return wrongCards;
    }

    public List<GameCard> getAllCards() {
        List<GameCard> cards = new ArrayList<>();
        cards.addAll(wrongCards);
        cards.add(new Random().nextInt(cards.size() + 1), correctCard);

        return cards;
    }

//    public boolean check(int position) {

//    }

}
