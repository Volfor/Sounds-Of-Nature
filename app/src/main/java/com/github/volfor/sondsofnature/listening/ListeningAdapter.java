package com.github.volfor.sondsofnature.listening;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.volfor.sondsofnature.models.GameCard;
import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ItemCardBinding;

import java.util.List;

import static com.github.volfor.sondsofnature.Utils.*;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class ListeningAdapter extends RecyclerView.Adapter<ListeningAdapter.ViewHolder> {

    public static final int ANIMALS = 546;
    public static final int TRANSPORT = 547;

    private List<GameCard> cards;

    public ListeningAdapter(Context context, int type) {
        switch (type) {
            case ANIMALS:
                cards = getAnimalCards(context);
                break;
            case TRANSPORT:
                cards = getTransportCards(context);
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
            binding.setItem(new CardItemViewModel(card));
        }
    }

    @BindingAdapter("image")
    public static void bindImage(ImageView v, @DrawableRes int id) {
        Glide.with(v.getContext())
                .load(id)
                .into(v);
    }

}
