package com.github.volfor.sondsofnature.models;

import java.util.List;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class GameCard {

    private String name;
    private int image;
    private List<Integer> sounds;

    public GameCard() {
    }

    public GameCard(String name, int image, List<Integer> sounds) {
        this.name = name;
        this.image = image;
        this.sounds = sounds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setSounds(List<Integer> sounds) {
        this.sounds = sounds;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public List<Integer> getSounds() {
        return sounds;
    }
}
