package com.github.volfor.sondsofnature.listening;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.volfor.sondsofnature.GameCard;
import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ItemCardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class ListeningAdapter extends RecyclerView.Adapter<ListeningAdapter.ViewHolder> {

    public static final int ANIMALS = 546;
    public static final int TRANSPORT = 547;

    private List<GameCard> cards;
    private Context context;

    public ListeningAdapter(Context context, int type) {
        this.context = context;

        switch (type) {
            case ANIMALS:
                getAnimalCards();
                break;
            case TRANSPORT:
                getTransportCards();
                break;
            default:
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindCard(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardBinding binding;

        public ViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        void bindCard(GameCard card) {
            binding.setGameCard(card);
        }
    }

    private void getAnimalCards() {
        cards = new ArrayList<>();
        cards.add(getCard("Tiger"));
        cards.add(getCard("Monkey"));
        cards.add(getCard("Sheep"));
        cards.add(getCard("Pig"));
        cards.add(getCard("Lion"));
        cards.add(getCard("Horse"));
        cards.add(getCard("Frog"));
        cards.add(getCard("Elephant"));
        cards.add(getCard("Dog"));
        cards.add(getCard("Cow"));
        cards.add(getCard("Chicken"));
        cards.add(getCard("Cat"));
    }

    private void getTransportCards() {
        cards = new ArrayList<>();
        cards.add(getCard("Airplane"));
        cards.add(getCard("Ambulance"));
        cards.add(getCard("Bicycle"));
        cards.add(getCard("Bus"));
        cards.add(getCard("Car"));
        cards.add(getCard("Fire engine"));
        cards.add(getCard("Helicopter"));
        cards.add(getCard("Motorcycle"));
        cards.add(getCard("Police car"));
        cards.add(getCard("Rocket"));
        cards.add(getCard("Ship"));
        cards.add(getCard("Train"));
    }

    private GameCard getCard(String name) {
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

    @BindingAdapter("image")
    public static void bindImage(ImageView v, @DrawableRes int id) {
        Glide.with(v.getContext())
                .load(id)
                .into(v);
    }

}
