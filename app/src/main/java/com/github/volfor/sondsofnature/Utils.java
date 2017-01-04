package com.github.volfor.sondsofnature;

import android.content.Context;
import android.content.res.Resources;

import com.github.volfor.sondsofnature.models.GameCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class Utils {

    public static List<GameCard> getTransportCards(Context context) {
        return getCards(context, new String[]{"Airplane", "Ambulance", "Bicycle", "Bus", "Car",
                "Fire engine", "Helicopter", "Motorcycle", "Police car", "Rocket", "Ship", "Train"}); // Add new cards here
    }

    public static List<GameCard> getAnimalCards(Context context) {
        return getCards(context, new String[]{"Tiger", "Monkey", "Sheep", "Pig", "Lion", "Horse",
                "Frog", "Elephant", "Dog", "Cow", "Chicken", "Cat"}); // Add new cards here
    }

    public static GameCard getCard(Context context, String name) {
        GameCard card = new GameCard();
        card.setName(name);

        name = name.toLowerCase().replace(" ", "_");

        Resources resources = context.getResources();
        List<Integer> sounds = new ArrayList<>();

        int id = resources.getIdentifier(name + "_1", "raw", context.getPackageName());
        int i = 2;
        while (id != 0) {
            sounds.add(id);
            id = resources.getIdentifier(name + "_" + i, "raw", context.getPackageName());
            i++;
        }

        card.setImage(resources.getIdentifier(name, "drawable", context.getPackageName()));
        card.setSounds(sounds);

        return card;
    }

    public static List<GameCard> getCards(Context context, String[] names) {
        List<GameCard> cards = new ArrayList<>();

        for (String name : names) {
            cards.add(getCard(context, name));
        }

        return cards;
    }

}
