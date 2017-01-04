package com.github.volfor.sondsofnature.listening;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.MediaPlayer;
import android.view.View;

import com.github.volfor.sondsofnature.models.GameCard;

import java.util.Random;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class CardItemViewModel extends BaseObservable {

    private GameCard card;

    public CardItemViewModel(GameCard card) {
        this.card = card;
    }

    @Bindable
    public String getName() {
        return card.getName();
    }

    @Bindable
    public int getImage() {
        return card.getImage();
    }

    public void onItemClick(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), card.getSounds()
                .get(new Random().nextInt(card.getSounds().size())));

        player.start();
    }

}
