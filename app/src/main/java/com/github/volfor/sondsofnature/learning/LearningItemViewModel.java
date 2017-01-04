package com.github.volfor.sondsofnature.learning;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.github.volfor.sondsofnature.events.ListenEvent;
import com.github.volfor.sondsofnature.models.GameCard;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class LearningItemViewModel extends BaseObservable {

    private GameCard card;

    public LearningItemViewModel(GameCard card) {
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
        int soundId = card.getSounds().get(new Random().nextInt(card.getSounds().size()));
        EventBus.getDefault().post(new ListenEvent(soundId));
    }

}
