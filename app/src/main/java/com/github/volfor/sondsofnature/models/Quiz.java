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

    private static Quiz instance = null;

    public enum Difficulty {
        EASY,
        NORMAL,
        HARD,
        EXTRA
    }

    private int correctCount = 0;
    private GameCard correctCard;
    private List<GameCard> wrongCards;

    private MediaPlayer player;

    public static Quiz getInstance() {
        if (instance == null) {
            instance = new Quiz();
        }
        return instance;
    }

    public void createTask(Context context) {
        List<GameCard> animals = Utils.getAnimalCards(context);
        Collections.shuffle(animals);

        correctCard = animals.get(0);
        wrongCards = new ArrayList<>();

        switch (getDifficulty()) {
            case EXTRA:
                wrongCards.add(animals.get(1));
                wrongCards.add(animals.get(2));
            case HARD:
                wrongCards.add(animals.get(3));
            case NORMAL:
                wrongCards.add(animals.get(4));
            case EASY:
                wrongCards.add(animals.get(5));
            default:
        }

        play(context);
    }

    public void play(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player = MediaPlayer.create(context, correctCard.getSounds()
                        .get(new Random().nextInt(correctCard.getSounds().size())));
                player.start();
            }
        }, 500);
    }

    public void replay() {
        if (player != null) {
            player.start();
        }
    }

    public boolean check(GameCard answer) {
        Utils.releasePlayer(player);

        if (answer.equals(correctCard)) {
            correctCount++;
            return true;
        } else {
            return false;
        }
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

    public Difficulty getDifficulty() {
        if (correctCount < 5) {
            return Difficulty.EASY;
        } else if (correctCount < 10) {
            return Difficulty.NORMAL;
        } else if (correctCount < 15) {
            return Difficulty.HARD;
        } else {
            return Difficulty.EXTRA;
        }
    }

    public static void clear() {
        instance = null;
    }

}
